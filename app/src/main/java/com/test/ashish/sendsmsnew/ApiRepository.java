package com.test.ashish.sendsmsnew;

import android.arch.lifecycle.MutableLiveData;

import com.test.ashish.sendsmsnew.pojo.Contact;
import com.test.ashish.sendsmsnew.pojo.Message;
import com.test.ashish.sendsmsnew.pojo.UpdateResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiRepository {

    private MutableLiveData<Boolean> contactApiStatus;
    private MutableLiveData<Boolean> messageApiStatus;
    private MutableLiveData<Boolean> updateMessageApiStatus;
    MutableLiveData<ArrayList<Contact>> allContacts  = new MutableLiveData<ArrayList<Contact>>();
    MutableLiveData<ArrayList<Message>> allMesssages = new MutableLiveData<ArrayList<Message>>();;

    public ApiRepository(){

    }



    public MutableLiveData<ArrayList<Contact>> getAllContatcs() {
        fetchContacts();
        return allContacts;
    }

    public MutableLiveData<ArrayList<Message>> getAllMessage() {
        fetchMessages();
        return allMesssages;
    }

    public MutableLiveData<Boolean> getContactFetchStatus() {
        if (contactApiStatus == null) {
            contactApiStatus = new MutableLiveData<Boolean>();
        }
        return contactApiStatus;
    }

    public MutableLiveData<Boolean> getMessageFetchStatus() {
        if (messageApiStatus == null) {
            messageApiStatus = new MutableLiveData<Boolean>();
        }
        return messageApiStatus;
    }

    public MutableLiveData<Boolean> getUpdateMessageFetchStatus() {
        if (updateMessageApiStatus == null) {
            updateMessageApiStatus = new MutableLiveData<Boolean>();
        }
        return updateMessageApiStatus;
    }

    public void fetchContacts() {

        retrofit2.Call<ArrayList<Contact>> call1 = RetrofitClient.getInstance().getApi().getContacts();
        call1.enqueue(new Callback<ArrayList<Contact>>() {

            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                if (response.code() == 200) {
                    ArrayList<Contact> reponse = response.body();
                    if(reponse.size()>0) {
                        allContacts.postValue(reponse);
                    }else{
                        contactApiStatus.setValue(false);
                    }
                }else{
                    contactApiStatus.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {
                contactApiStatus.setValue(false);
            }
        });
    }


    public void fetchMessages() {

        retrofit2.Call<ArrayList<Message>> call1 = RetrofitClient.getInstance().getApi().getMessages();
        call1.enqueue(new Callback<ArrayList<Message>>() {

            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                if (response.code() == 200) {
                    ArrayList<Message> reponse = response.body();
                    if(reponse.size()>0) {
                        allMesssages.postValue(reponse);
                    }else{
                        messageApiStatus.setValue(false);
                    }
                }else{
                    messageApiStatus.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {
                messageApiStatus.setValue(false);
            }
        });
    }


    public void updateMessage(String useName, String userMobile, String userMessage) {

        retrofit2.Call<UpdateResponse> call1 = RetrofitClient.getInstance().getApi().updateMessage(useName,userMobile,userMessage);
        call1.enqueue(new Callback<UpdateResponse>() {

            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.code() == 200) {
                    UpdateResponse reponse = response.body();
                    if(reponse.status.equalsIgnoreCase("1")) {
                        updateMessageApiStatus.setValue(true);
                    }else{
                        updateMessageApiStatus.setValue(false);
                    }
                }else{
                    updateMessageApiStatus.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                updateMessageApiStatus.setValue(false);
            }
        });
    }


}
