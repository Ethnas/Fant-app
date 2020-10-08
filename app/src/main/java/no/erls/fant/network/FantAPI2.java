package no.erls.fant.network;

import java.math.BigDecimal;
import java.util.List;

import no.erls.fant.itemuser.Item;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface FantAPI2 {
    @FormUrlEncoded
    @POST("auth/create")
    public Call<ResponseBody> createUser(@Field("uid") String username, @Field("pwd") String password, @Field("email") String email);

    @GET("auth/login")
    public Call<ResponseBody> userLogin(@Query("uid") String username, @Query("pwd") String password);

    @Multipart
    @POST("shop/additem")
    public Call<ResponseBody> addItem(@Header("Autorization") String authHeader, @Part("itemTitle") String itemTitle, @Part("itemPrice") String itemPrice, @Part("itemDesc") String itemDesc);

    @POST("shop/buyitem")
    public Call<ResponseBody> buyItem(@Query("id") String itemId);

    @DELETE("shop/deleteitem")
    public Call<ResponseBody> removeItem(@Query("itemId") String itemId);

    @GET("auth/currentuser")
    public Call<ResponseBody> currentUser(@Header("Authorization") String auth);

    @GET("shop/getitems")
    public Call<List<Item>> getItems();
}
