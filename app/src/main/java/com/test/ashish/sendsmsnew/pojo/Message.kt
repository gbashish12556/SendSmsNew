package com.test.ashish.sendsmsnew.pojo

import com.google.gson.annotations.SerializedName

class Message {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("mobile")
    var mobile: String? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("sms_time")
    var sms_time: String? = null

}

