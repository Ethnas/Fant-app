package no.erls.fant.itemuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import no.erls.fant.network.FantAPI;
import no.erls.fant.network.FantAPI2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemList {
    private static ItemList ItemList;
    private static FantAPI2 fantApi2;
    private MutableLiveData<List<Item>> itemsResponse;

    public static ItemList getInstance() {
        if (ItemList == null) {
            ItemList = new ItemList();
        }
        return ItemList;
    }

    private ItemList() {
    }

    public void getItems() {
        Call<List<Item>> call = FantAPI.getSINGLETON().getApi().getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    itemsResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Item>> getItemResponse() {
        return itemsResponse;
    }
}
