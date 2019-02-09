package com.test.ashish.sendsmsnew.pojo

import com.google.gson.annotations.SerializedName

class Message {

    constructor(name:String, mobile:String, message: String, sms_time:String){
        this.name = name
        this.mobile = mobile
        this.message = message
        this.sms_time = sms_time
    }
    @SerializedName("name")
    var name: String? = null
    @SerializedName("mobile")
    var mobile: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("sms_time")
    var sms_time: String? = null

}

