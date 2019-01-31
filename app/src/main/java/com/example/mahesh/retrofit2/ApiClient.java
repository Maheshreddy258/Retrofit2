package com.example.mahesh.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static Retrofit retrofit = null;


    public static Retrofit getClient() {


        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }

        return retrofit;
    }
}
