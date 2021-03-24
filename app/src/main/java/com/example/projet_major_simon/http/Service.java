package com.example.projet_major_simon.http;

import com.example.projet_major_simon.Tache;
import com.example.projet_major_simon.transfer.AddTaskRequest;
import com.example.projet_major_simon.transfer.HomeItemResponse;
import com.example.projet_major_simon.transfer.SigninRequest;
import com.example.projet_major_simon.transfer.SigninResponse;
import com.example.projet_major_simon.transfer.SignupRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    @POST("api/id/signup")
    Call<SignupRequest> Signuprequest(@Body SignupRequest signupRequest);

    @POST("api/id/signin")
    Call<SigninRequest> Signinrequest(@Body SigninRequest signinRequest);

    @POST("api/id/signout")
    Call<String> Signoutrequest();


    @POST("api/add")
    Call<AddTaskRequest> AddTask(@Body AddTaskRequest addTaskRequest);


    @GET("api/home")
    Call<List<HomeItemResponse>> HomeItemResponse();



}
