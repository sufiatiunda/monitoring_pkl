package com.example.monitoring_pkl.api;

import static com.example.monitoring_pkl.utils.config.BASEURL;

import kotlin.annotation.Retention;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiserver {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }

    return retrofit;
}
}
