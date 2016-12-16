package android.db.boxme.utils;

import android.db.boxme.model.Authorization;
import android.db.boxme.model.GitEvent;
import android.db.boxme.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Caritor on 12/14/2016.
 */
public interface GithubService {
//    @POST("user/login")
//    Call<LoginActivity.UserLoginTask.TestUser> login(@Body LoginActivity.UserLoginTask.LoginFormRequest body);
    @GET("user")
    Call<User> authenticateUser( @Header("Authorization") String authorization );//String basicAuth = "Basic " + Base64.encodeToString(String.format("%s:%s", "your_user_name", "your_password").getBytes(), Base64.NO_WRAP);
    @PUT("authorizations/clients/{clientID}")
    Call<Authorization> getOrCreateAuthorization(@Path("clientID") String clientID);
    @GET("users/{username}/events")
    Call<List<GitEvent>> getEvents(@Path("username") String username , @Query("page") int page);
}
