package com.test.ashish.sendsmsnew

import android.arch.lifecycle.MutableLiveData

import com.test.ashish.sendsmsnew.pojo.Contact
import com.test.ashish.sendsmsnew.pojo.Message
import com.test.ashish.sendsmsnew.pojo.UpdateResponse

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiRepository {

    private var contactApiStatus: MutableLiveData<Boolean>? = null
    private var messageApiStatus: MutableLiveData<Boolean>? = null
    private var updateMessageApiStatus: MutableLiveData<Boolean>? = null
    private val allContacts = MutableLiveData<ArrayList<Contact>>()
    private val allMesssages = MutableLiveData<ArrayList<Message>>()

    val allContatcs: MutableLiveData<ArrayList<Contact>>
        get() {
            fetchContacts()
            return allContacts
        }

    val allMessage: MutableLiveData<ArrayList<Message>>
        get() {
            fetchMessages()
            return allMesssages
        }

    val contactFetchStatus: MutableLiveData<Boolean>
        get() {
            if (contactApiStatus == null) {
                contactApiStatus = MutableLiveData()
            }
            return contactApiStatus
        }

    val messageFetchStatus: MutableLiveData<Boolean>
        get() {
            if (messageApiStatus == null) {
                messageApiStatus = MutableLiveData()
            }
            return messageApiStatus
        }

    val updateMessageFetchStatus: MutableLiveData<Boolean>
        get() {
            if (updateMessageApiStatus == null) {
                updateMessageApiStatus = MutableLiveData()
            }
            return updateMessageApiStatus
        }

    fun fetchContacts() {

        val call1 = RetrofitClient.instance.api.contacts
        call1.enqueue(object : Callback<ArrayList<Contact>> {

            override fun onResponse(call: Call<ArrayList<Contact>>, response: Response<ArrayList<Contact>>) {
                if (response.code() == 200) {
                    val reponse = response.body()
                    if (reponse.size > 0) {
                        allContacts.postValue(reponse)
                    } else {
                        contactApiStatus!!.setValue(false)
                    }
                } else {
                    contactApiStatus!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<ArrayList<Contact>>, t: Throwable) {
                contactApiStatus!!.value = false
            }
        })
    }


    fun fetchMessages() {

        val call1 = RetrofitClient.instance.api.messages
        call1.enqueue(object : Callback<ArrayList<Message>> {

            override fun onResponse(call: Call<ArrayList<Message>>, response: Response<ArrayList<Message>>) {
                if (response.code() == 200) {
                    val reponse = response.body()
                    if (reponse.size > 0) {
                        allMesssages.postValue(reponse)
                    } else {
                        messageApiStatus!!.setValue(false)
                    }
                } else {
                    messageApiStatus!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<ArrayList<Message>>, t: Throwable) {
                messageApiStatus!!.value = false
            }
        })
    }


    fun updateMessage(useName: String, userMobile: String, userMessage: String) {

        val call1 = RetrofitClient.instance.api.updateMessage(useName, userMobile, userMessage)
        call1.enqueue(object : Callback<UpdateResponse> {

            override fun onResponse(call: Call<UpdateResponse>, response: Response<UpdateResponse>) {
                if (response.code() == 200) {
                    val reponse = response.body()
                    if (reponse.status!!.equals("1", ignoreCase = true)) {
                        updateMessageApiStatus!!.setValue(true)
                    } else {
                        updateMessageApiStatus!!.setValue(false)
                    }
                } else {
                    updateMessageApiStatus!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                updateMessageApiStatus!!.value = false
            }
        })
    }


}
