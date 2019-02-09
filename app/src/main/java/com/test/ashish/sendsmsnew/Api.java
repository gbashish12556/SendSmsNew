package com.test.ashish.sendsmsnew;

import com.test.ashish.sendsmsnew.pojo.Contact;
import com.test.ashish.sendsmsnew.pojo.Message;
import com.test.ashish.sendsmsnew.pojo.UpdateResponse;

import java.util.ArrayList;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("exec?getType=contact")
    Call<ArrayList<Contact>> getContacts();

    @GET("exec?getType=message")
    Call<ArrayList<Message>> getMessages();

    @POST("exec")
    @FormUrlEncoded
    Call<UpdateResponse> updateMessage(@Field("user_name") String userName, @Field("user_mobile") String userMobile, @Field("user_message") String userMessage);

}