package com.webplate.retrofit;



import com.webplate.model.UserData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<ArrayList<UserData>> getAllUserData();
}
