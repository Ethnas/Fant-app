package no.erls.fant.ui.market;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import no.erls.fant.itemuser.Item;
import no.erls.fant.itemuser.ItemList;

public class MarketViewModel extends AndroidViewModel {
    private MutableLiveData<String> text;
    private LiveData<List<Item>> itemsResponse;
    private ItemList itemList;


    public MarketViewModel(@NonNull Application application) {
        super(application);
        text = new MutableLiveData<String>();
        text.setValue("Shit fuuck");

        if (itemsResponse == null) {
            itemList = ItemList.getInstance();
            itemsResponse = itemList.getItemResponse();
        }
    }

    public void getItems() {
        itemList.getItems();
    }

    public LiveData<List<Item>> getItemListLiveData() {
        return itemsResponse;
    }

    public LiveData<String> getText() {
        return text;
    }
}
