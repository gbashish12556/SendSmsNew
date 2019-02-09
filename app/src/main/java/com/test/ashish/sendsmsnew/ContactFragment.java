package com.test.ashish.sendsmsnew;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.test.ashish.sendsmsnew.pojo.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment implements ContactsRecyclerViewAdapter.ClickListener{

    private ViewModel viewModel;
    private RecyclerView recyclerView;
    private ArrayList<Contact> allContacts;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = (ViewModel) ViewModelProviders.of(this).get(ViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        allContacts = new ArrayList<>() ;

        final ContactsRecyclerViewAdapter noteAdapter = new ContactsRecyclerViewAdapter(this, allContacts);

        recyclerView.setAdapter(noteAdapter);

        viewModel.getContactFetchStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean == false){
                    Toast.makeText(getActivity(),"No Match Found", Toast.LENGTH_LONG).show();
                }
            }
        });

        viewModel.getContacts().observe(this,new Observer<ArrayList<Contact>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Contact> contacts) {
                //Data Changed
                if(contacts.size()>0) {
                    noteAdapter.setData(contacts);
                }
            }
        });
    }

    @Override
    public void launchIntent(String userName, String userMobile) {
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        intent.putExtra("user_name",userName);
        intent.putExtra("user_mobile", userMobile);
        startActivity(intent);
    }
}
