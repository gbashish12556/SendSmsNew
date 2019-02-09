package com.test.ashish.sendsmsnew.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.test.ashish.sendsmsnew.R
import com.test.ashish.sendsmsnew.pojo.Contact

import java.util.ArrayList


class ContactsRecyclerViewAdapter(clickListener: ClickListener, private val data: ArrayList<Contact>) : RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder>() {
    private val clickListener: ContactsRecyclerViewAdapter.ClickListener

    init {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_recycler_view_list_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = data[position].name
        holder.txtMobile.text = data[position].mobile
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView
        private val txtMobile: TextView
        private val linearLayout: LinearLayout

        init {

            txtName = itemView.findViewById(R.id.txtName)
            txtMobile = itemView.findViewById(R.id.txtMobile)
            linearLayout = itemView.findViewById(R.id.linearLayout)

            linearLayout.setOnClickListener { clickListener.launchIntent(data[adapterPosition].name, data[adapterPosition].mobile) }
        }
    }

    interface ClickListener {
        fun launchIntent(userName: String, userMobile: String?)
    }

    fun setData(data: List<Contact>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}