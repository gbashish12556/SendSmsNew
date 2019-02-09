package com.test.ashish.sendsmsnew.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.test.ashish.sendsmsnew.R
import com.test.ashish.sendsmsnew.pojo.Message

import java.util.ArrayList


class MessageRecyclerViewAdapter(private val data: ArrayList<Message>) : RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_recycler_view_list_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = data[position].name
        holder.txtMobile.text = data[position].mobile
        holder.txtMessage.text = data[position].message
        holder.txtSmsTime.text = data[position].sms_time
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView
        private val txtMobile: TextView
        private val txtSmsTime: TextView
        private val txtMessage: TextView

        init {

            txtName = itemView.findViewById(R.id.txtName)
            txtMobile = itemView.findViewById(R.id.txtMobile)
            txtMessage = itemView.findViewById(R.id.txtMessage)
            txtSmsTime = itemView.findViewById(R.id.txtTimeOfSms)
        }
    }

    fun setData(data: List<Message>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}