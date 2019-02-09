package com.test.ashish.sendsmsnew;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://script.google.com/macros/s/AKfycbysPlhA8ta46MiDJZEqslIbUZJSWXssrQsXGloQ59cEDODZyhA/";
    private static RetrofitClient mRetrofitInstance;
    private Retrofit retrofit;

    private RetrofitClient(){

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized RetrofitClient getInstance(){

        if(mRetrofitInstance == null) {
            mRetrofitInstance = new RetrofitClient();
        }

        return mRetrofitInstance;

    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}