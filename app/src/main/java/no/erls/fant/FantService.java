package no.erls.fant;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class FantService implements Response.ErrorListener {
    static FantService SINGLETON;

    User user;

    String token;
    RequestQueue requestQueue;


    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Error" + error);
    }
}
