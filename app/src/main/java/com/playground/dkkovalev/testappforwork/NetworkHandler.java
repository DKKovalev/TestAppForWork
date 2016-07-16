package com.playground.dkkovalev.testappforwork;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DKKovalev on 16.07.2016.
 */
public class NetworkHandler {
    private static final String URL = "https://obscure-shelf-31484.herokuapp.com/";

    private View view;

    private UserFetcher userFetcher;

    public NetworkHandler(View view) {
        this.view = view;
    }

    public ArrayList<User> getUser() {
        final ArrayList<User> users = new ArrayList<>();

        Call<List<User>> call = setupRetrofit().create(RESTMethods.class).getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users.addAll(response.body());
                view.showListOfUsers(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });

        return users;

    }

    public void getDetailedInfo(int id) {

        Call<User> call = setupRetrofit().create(RESTMethods.class).getDetailedInfo(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userFetcher.fetchUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void sendInfo(Info info) {
        Call<Info> call = setupRetrofit().create(RESTMethods.class).sendInfo(info);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });
    }

    public void setUserFetcher(UserFetcher userFetcher) {
        this.userFetcher = userFetcher;
    }

    private Retrofit setupRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
