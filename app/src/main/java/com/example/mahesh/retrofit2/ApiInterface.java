package com.example.mahesh.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

           @GET("api/users?page=2")
    Call<ListUser> getServiceResponse();
}
