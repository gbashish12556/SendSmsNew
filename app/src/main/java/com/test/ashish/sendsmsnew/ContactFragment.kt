package com.test.ashish.sendsmsnew


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.test.ashish.sendsmsnew.adapter.ContactsRecyclerViewAdapter
import com.test.ashish.sendsmsnew.pojo.Contact

import java.util.ArrayList
import java.util.Date


/**
 * A simple [Fragment] subclass.
 */
class ContactFragment : Fragment(), ContactsRecyclerViewAdapter.ClickListener {

    private var viewModel: ViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var allContacts: ArrayList<Contact>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        allContacts = ArrayList()

        val noteAdapter = ContactsRecyclerViewAdapter(this, allContacts!!)

        recyclerView!!.adapter = noteAdapter

        viewModel!!.contactFetchStatus.observe(this, Observer { aBoolean ->
            if (aBoolean == false) {
                Toast.makeText(activity, "No Match Found", Toast.LENGTH_LONG).show()
            }
        })

        viewModel!!.contacts.observe(this, Observer { contacts ->
            //Data Changed
            if (contacts!!.size > 0) {
                noteAdapter.setData(contacts)
            }
        })
    }

    override fun launchIntent(userName: String, userMobile: String?) {
        val intent = Intent(activity, ContactDetailActivity::class.java)
        intent.putExtra("user_name", userName)
        intent.putExtra("user_mobile", userMobile)
        startActivity(intent)
    }
}// Required empty public constructor
