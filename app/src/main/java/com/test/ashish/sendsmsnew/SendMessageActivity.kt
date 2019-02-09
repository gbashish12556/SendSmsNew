package com.test.ashish.sendsmsnew

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.util.Random

class SendMessageActivity : AppCompatActivity() {

    private var viewModel: ViewModel? = null

    private var userName = ""
    private var userMobile = ""
    private var otp = ""
    private var message = ""

    private var messageEditText: EditText? = null
    private var sendMessageButton: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        messageEditText = findViewById(R.id.message)
        sendMessageButton = findViewById(R.id.sendButton)

        if (intent != null) {
            userName = intent.getStringExtra("user_name")
            userMobile = intent.getStringExtra("user_mobile")
        }

        val rand = Random()

        otp = rand.nextInt(10000).toString()
        message = "Hi your otp is : $otp"
        messageEditText!!.setText(message)

        messageEditText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                message = editable.toString()
            }

        })

        viewModel!!.updateMessageFetchStatus.observe(this, Observer { aBoolean ->
            if (aBoolean == false) {
                Toast.makeText(this@SendMessageActivity, "Message Failed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@SendMessageActivity, "Message Sent", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@SendMessageActivity, MainActivity::class.java))
            }
        })
        sendMessageButton!!.setOnClickListener {
            if (message.equals("", ignoreCase = true)) {
                Toast.makeText(this@SendMessageActivity, "Message Field Can Not Be Blank", Toast.LENGTH_LONG).show()
            } else {
                viewModel!!.updateMessage(userName, userMobile, message)
            }
        }
    }
}
