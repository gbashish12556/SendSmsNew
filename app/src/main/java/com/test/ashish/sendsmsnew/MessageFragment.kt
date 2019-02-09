package com.test.ashish.sendsmsnew


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.test.ashish.sendsmsnew.adapter.ContactsRecyclerViewAdapter
import com.test.ashish.sendsmsnew.adapter.MessageRecyclerViewAdapter
import com.test.ashish.sendsmsnew.pojo.Contact
import com.test.ashish.sendsmsnew.pojo.Message

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class MessageFragment : Fragment() {


    private var viewModel: ViewModel? = null
    private var recyclerView: RecyclerView? = null
    private var allMessage: ArrayList<Message>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        recyclerView!!.layoutManager = LinearLayoutManager(activity)

        allMessage = ArrayList()

        val noteAdapter = MessageRecyclerViewAdapter(allMessage!!)

        recyclerView!!.adapter = noteAdapter

        viewModel!!.messageFetchStatus.observe(this, Observer { aBoolean ->
            if (aBoolean == false) {
                Toast.makeText(activity, "No Match Found", Toast.LENGTH_LONG).show()
            }
        })

        viewModel!!.messages.observe(this, Observer { contacts ->
            if (contacts!!.size > 0) {
                noteAdapter.setData(contacts)
            }
        })
    }

}// Required empty public constructor
