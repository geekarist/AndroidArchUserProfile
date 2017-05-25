package fr.xebia.cpele.userprofile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface UserProfileApi {

    @GET("/users/{user}")
    Call<User> fetchUser(@Path("user") String userId);
}
