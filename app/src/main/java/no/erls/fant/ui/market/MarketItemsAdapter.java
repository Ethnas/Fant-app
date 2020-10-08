package no.erls.fant.ui.market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import no.erls.fant.R;
import no.erls.fant.itemuser.Item;

public class MarketItemsAdapter extends RecyclerView.Adapter<MarketItemsAdapter.MarketItemsViewHolder> {
    OnClickListener listener = position -> {};
    private List<Item> listOfItems;

    public MarketItemsAdapter() {
        this.listOfItems = new ArrayList<>();
    }

    public List<Item> getMarketItems() {
        return listOfItems;
    }

    public void setMarketItems(List<Item> items) {
        this.listOfItems = items;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    interface OnClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public MarketItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist, parent, false);
        return new MarketItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketItemsViewHolder holder, int position) {
        Item items = listOfItems.get(position);
        holder.itemTitle.setText(items.getItemTitle());
        holder.itemPrice.setText(items.getItemPrice().toString());
        holder.itemDesc.setText(items.getItemDesc());
    }

    @Override
    public int getItemCount() {
        return getMarketItems().size();
    }

   class MarketItemsViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemPrice;
        TextView itemDesc;

        public MarketItemsViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(v -> listener.onClick(getAdapterPosition()));
            this.itemTitle = view.findViewById(R.id.itemlist_name);
            this.itemPrice = view.findViewById(R.id.itemlist_price);
            this.itemDesc = view.findViewById(R.id.itemlist_desc);
        }
    }
}
