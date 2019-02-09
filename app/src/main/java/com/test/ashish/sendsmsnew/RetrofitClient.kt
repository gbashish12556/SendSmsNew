package com.test.ashish.sendsmsnew

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private val retrofit: Retrofit

    val api: Api
        get() = retrofit.create(Api::class.java)

    init {

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {

        private val BASE_URL = "https://script.google.com/macros/s/AKfycbysPlhA8ta46MiDJZEqslIbUZJSWXssrQsXGloQ59cEDODZyhA/"
        private var mRetrofitInstance: RetrofitClient? = null

        val instance: RetrofitClient
            @Synchronized get() {

                if (mRetrofitInstance == null) {
                    mRetrofitInstance = RetrofitClient()
                }

                return mRetrofitInstance as RetrofitClient

            }
    }
}