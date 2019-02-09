package com.test.ashish.sendsmsnew.pojo;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("name")
    public String name;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("message")
    public String message;
    @SerializedName("sms_time")
    public String sms_time;

}

