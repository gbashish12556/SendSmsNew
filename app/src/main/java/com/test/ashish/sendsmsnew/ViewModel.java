package com.test.ashish.sendsmsnew;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.test.ashish.sendsmsnew.pojo.Contact;
import com.test.ashish.sendsmsnew.pojo.Message;

import java.util.ArrayList;


public class ViewModel extends AndroidViewModel {

    private ApiRepository apiRepository;
    private MutableLiveData<Boolean> contactApiStatus;
    private MutableLiveData<Boolean> messageApiStatus;
    private MutableLiveData<Boolean> updateMessageApiStatus;

    public ViewModel(@NonNull Application application) {
        super(application);
        apiRepository = new ApiRepository();
    }


    public MutableLiveData<ArrayList<Contact>> getContacts(){
        return  apiRepository.getAllContatcs();
    }

    public MutableLiveData<Boolean> getContactFetchStatus() {
        if (contactApiStatus == null) {
            contactApiStatus = apiRepository.getContactFetchStatus();
        }
        return contactApiStatus;
    }

    public MutableLiveData<ArrayList<Message>> getMessages(){
        return  apiRepository.getAllMessage();
    }

    public MutableLiveData<Boolean> getMessageFetchStatus() {
        if (messageApiStatus == null) {
            messageApiStatus = apiRepository.getMessageFetchStatus();
        }
        return messageApiStatus;
    }

    public MutableLiveData<Boolean> getUpdateMessageFetchStatus(){
        if (updateMessageApiStatus == null) {
            updateMessageApiStatus = apiRepository.getUpdateMessageFetchStatus();
        }
        return updateMessageApiStatus;
    }

    public void updateMessage(String useName, String userMobile, String userMessage){
        apiRepository.updateMessage(useName, userMobile, userMessage);
    }


}