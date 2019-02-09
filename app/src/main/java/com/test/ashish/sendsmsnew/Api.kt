package com.test.ashish.sendsmsnew

import com.test.ashish.sendsmsnew.pojo.Contact
import com.test.ashish.sendsmsnew.pojo.Message
import com.test.ashish.sendsmsnew.pojo.UpdateResponse

import java.util.ArrayList

import retrofit2.Call

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @get:GET("exec?getType=contact")
    val contacts: Call<ArrayList<Contact>>

    @get:GET("exec?getType=message")
    val messages: Call<ArrayList<Message>>

    @POST("exec")
    @FormUrlEncoded
    fun updateMessage(@Field("user_name") userName: String, @Field("user_mobile") userMobile: String, @Field("user_message") userMessage: String): Call<UpdateResponse>

}