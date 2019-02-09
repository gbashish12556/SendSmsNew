package com.test.ashish.sendsmsnew

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

import com.test.ashish.sendsmsnew.pojo.Contact
import com.test.ashish.sendsmsnew.pojo.Message

import java.util.ArrayList


class ViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository: ApiRepository
    private var contactApiStatus: MutableLiveData<Boolean>? = null
    private var messageApiStatus: MutableLiveData<Boolean>? = null
    private var updateMessageApiStatus: MutableLiveData<Boolean>? = null


    val contacts: MutableLiveData<ArrayList<Contact>>
        get() = apiRepository.allContatcs

    val contactFetchStatus: MutableLiveData<Boolean>
        get() {
            if (contactApiStatus == null) {
                contactApiStatus = apiRepository.contactFetchStatus
            }
            return contactApiStatus
        }

    val messages: MutableLiveData<ArrayList<Message>>
        get() = apiRepository.allMessage

    val messageFetchStatus: MutableLiveData<Boolean>
        get() {
            if (messageApiStatus == null) {
                messageApiStatus = apiRepository.messageFetchStatus
            }
            return messageApiStatus
        }

    val updateMessageFetchStatus: MutableLiveData<Boolean>
        get() {
            if (updateMessageApiStatus == null) {
                updateMessageApiStatus = apiRepository.updateMessageFetchStatus
            }
            return updateMessageApiStatus
        }

    init {
        apiRepository = ApiRepository()
    }

    fun updateMessage(useName: String, userMobile: String, userMessage: String) {
        apiRepository.updateMessage(useName, userMobile, userMessage)
    }


}