package com.yascode.pemadamkebakaran;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by caksono21 on 02/12/17.
 */

public interface ApiInterface {

    @GET("v1/emergency/pospemadam/")
    Call<Pemadam> getPemadam(@Header("Authorization") String token);
}
