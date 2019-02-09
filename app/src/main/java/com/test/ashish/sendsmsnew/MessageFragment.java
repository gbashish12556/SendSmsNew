package com.test.ashish.sendsmsnew;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.ashish.sendsmsnew.adapter.ContactsRecyclerViewAdapter;
import com.test.ashish.sendsmsnew.adapter.MessageRecyclerViewAdapter;
import com.test.ashish.sendsmsnew.pojo.Contact;
import com.test.ashish.sendsmsnew.pojo.Message;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private ArrayList<Message> allMessage;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = (ViewModel) ViewModelProviders.of(this).get(ViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        allMessage = new ArrayList<>() ;

        final MessageRecyclerViewAdapter noteAdapter = new MessageRecyclerViewAdapter(allMessage);

        recyclerView.setAdapter(noteAdapter);

        viewModel.getMessageFetchStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean == false){
                    Toast.makeText(getActivity(),"No Match Found", Toast.LENGTH_LONG).show();
                }
            }
        });

        viewModel.getMessages().observe(this,new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Message> contacts) {
                if(contacts.size()>0) {
                    noteAdapter.setData(contacts);
                }
            }
        });
    }

}
