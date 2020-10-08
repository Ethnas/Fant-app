package no.erls.fant.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RequestGen<T> extends Request<T> {
    private final Map<String, String> headers;
    private final Class<T> gsonClassThing;
    private final Response.Listener<T> listener;
    private final Gson gson = new Gson();

    public RequestGen(String requestUrl, int method, Class<T> gsonClassThing, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, requestUrl, errorListener);
        this.gsonClassThing = gsonClassThing;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            System.out.println(json);
            return Response.success(gson.fromJson(json, gsonClassThing), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }
}
