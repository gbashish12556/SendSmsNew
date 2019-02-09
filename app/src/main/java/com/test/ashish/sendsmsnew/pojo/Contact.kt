package com.test.ashish.sendsmsnew.pojo

import com.google.gson.annotations.SerializedName

class Contact {

    @SerializedName("name")
    var name: String
    @SerializedName("mobile")
    var mobile: String? = null


}
