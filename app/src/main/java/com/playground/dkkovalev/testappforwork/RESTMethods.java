package com.playground.dkkovalev.testappforwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public interface RESTMethods {
    @GET("users.json")
    Call<List<User>> getUsers();

    @GET("users/{id}.json")
    Call<User> getDetailedInfo(@Path("id") int id);

    @POST("uploads")
    Call<Info> sendInfo(@Body Info info);
}
