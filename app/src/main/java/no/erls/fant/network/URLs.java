package no.erls.fant.network;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Class for containing all the URLs
 */

public class URLs {
    public static final String BASE_URL = "http://localhost:8080/ID303911-oblig1/api/";

    public static final String LOGIN_URL = BASE_URL + "auth/login?uid=%s&pwd=%s";
    public static final String REGISTER_URL = BASE_URL + "auth/create";
    public static final String CURRENT_USER_URL = BASE_URL + "auth/currentuser";

    public static final Response.ErrorListener newEL = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    };
}
