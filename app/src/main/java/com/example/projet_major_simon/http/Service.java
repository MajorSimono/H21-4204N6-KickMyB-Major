package com.example.projet_major_simon.http;

import com.example.projet_major_simon.transfer.SigninRequest;
import com.example.projet_major_simon.transfer.SigninResponse;
import com.example.projet_major_simon.transfer.SignupRequest;

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

}
