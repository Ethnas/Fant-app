package no.erls.fant.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FantAPI {
    private static FantAPI SINGLETON;
    Retrofit retrofit = null;

    public FantAPI() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder().baseUrl(URLs.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }

    public static synchronized FantAPI getSINGLETON() {
        if (SINGLETON == null) {
            SINGLETON = new FantAPI();
        }
        return SINGLETON;
    }

    public FantAPI2 getApi() {
        return retrofit.create(FantAPI2.class);
    }
}
