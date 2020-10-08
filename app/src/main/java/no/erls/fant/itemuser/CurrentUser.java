package no.erls.fant.itemuser;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;

import no.erls.fant.network.RequestGen;
import no.erls.fant.network.URLs;

public class CurrentUser {

    //Status of whether or not there's an instance of a user
    private static CurrentUser userInstance = null;
    private boolean userActive = false;
    private String jwt;
    private User user;

    //Checks for instance, if none -> create.
    public static CurrentUser getUserInstance() {if (userInstance == null) {
        userInstance = new CurrentUser();
    }
        return userInstance;
    }

    //Checks if user is currently logged inn
    public boolean userActive() {
        return userActive();
    }

    public void setUserActive(boolean userActive1) {
        userActive = userActive1;
    }

    //Get JWT Token from server
    public HashMap<String, String> getJWTToken() {
        HashMap<String, String> jwtToken = new HashMap<>();
        jwtToken.put("Authorization", "Bearer " + jwt);
        return jwtToken;
    }

    public void getCurrentUser() {
        RequestGen<User> requestGen = new RequestGen<>(
                URLs.CURRENT_USER_URL, Request.Method.GET,
                User.class, getJWTToken(),
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        user = response;

                    }
                },
                URLs.newEL
        );
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
