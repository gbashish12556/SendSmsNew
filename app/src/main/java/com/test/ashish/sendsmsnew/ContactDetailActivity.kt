package com.test.ashish.sendsmsnew

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ContactDetailActivity : Activity() {

    private var userNameTextView: TextView? = null
    private var userMobileTextView: TextView? = null
    private var sendMessageButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        userNameTextView = findViewById(R.id.userName)
        userMobileTextView = findViewById(R.id.userMobile)
        sendMessageButton = findViewById(R.id.sendButton)

        if (intent != null) {
            userNameTextView!!.text = intent.getStringExtra("user_name")
            userMobileTextView!!.text = intent.getStringExtra("user_mobile")
        }

        sendMessageButton!!.setOnClickListener {
            val intent = Intent(this@ContactDetailActivity, SendMessageActivity::class.java)
            intent.putExtra("user_name", userNameTextView!!.text.toString())
            intent.putExtra("user_mobile", userMobileTextView!!.text.toString())
            startActivity(intent)
        }

    }
}
