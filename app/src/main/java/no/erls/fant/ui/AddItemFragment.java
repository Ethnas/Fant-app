package no.erls.fant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.erls.fant.R;
import no.erls.fant.itemuser.Item;
import no.erls.fant.itemuser.User;
import no.erls.fant.network.FantAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddItemFragment extends Fragment {

    TextView itemTitleV;
    TextView itemPriceV;
    TextView itemDescV;
    private Item items = new Item();
    private User user = new User();

    public AddItemFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public void addItem() {
        String itemTitle = itemTitleV.getText().toString();
        String itemPrice = itemPriceV.getText().toString();
        String itemDesc = itemDescV.getText().toString();
        String authHeader = user.getJwt();

        if (itemTitle.isEmpty()) {
            itemTitleV.setError("Title is missing");
            itemTitleV.requestFocus();
            return;
        }
        if (itemPrice.isEmpty()) {
            itemPriceV.setError("Price is missing");
            itemPriceV.requestFocus();
            return;
        }
        if (itemDesc.isEmpty()) {
            itemDescV.setError("Description is missing");
            itemDescV.requestFocus();
            return;
        }
        if (authHeader == null) {
            itemDescV.setError("You're not logged inn");
            itemDescV.requestFocus();
            Toast.makeText(getActivity(), "Not logged inn", Toast.LENGTH_LONG).show();
            System.out.println("Missing jwt");
        }

        Call<ResponseBody> call = FantAPI.getSINGLETON().getApi()
                .addItem(authHeader,itemTitle,itemPrice,itemDesc);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Item added", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body().toString());

                    Navigation.findNavController(getView()).popBackStack();
                }
                else {
                    Toast.makeText(getActivity(),"Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container,false);

        itemTitleV = view.findViewById(R.id.additem_name);
        itemPriceV = view.findViewById(R.id.additem_price);
        itemDescV = view.findViewById(R.id.additem_desc);
        Button submitV = (Button) view.findViewById(R.id.leggtil);

        submitV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.leggtil:
                        addItem();
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }
}