package com.test.ashish.sendsmsnew;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class SendMessageActivity extends AppCompatActivity {

    private ViewModel viewModel;

    private String userName = "", userMobile = "", otp = "", message = "";

    private EditText messageEditText;
    private Button sendMessageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        messageEditText = findViewById(R.id.message);
        sendMessageButton = findViewById(R.id.sendButton);

        if(getIntent() != null){
            userName = getIntent().getStringExtra("user_name");
            userMobile = getIntent().getStringExtra("user_mobile");
        }

        Random rand = new Random();

        otp = String.valueOf(rand.nextInt(10000));
        message = "Hi your otp is : "+otp;
        messageEditText.setText(message);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                message = editable.toString();
            }

        });

        viewModel.getUpdateMessageFetchStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean == false){
                    Toast.makeText(SendMessageActivity.this,"Message Failed", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SendMessageActivity.this,"Message Sent", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SendMessageActivity.this, MainActivity.class));
                }
            }
        });
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message.equalsIgnoreCase("")) {
                    Toast.makeText(SendMessageActivity.this,"Message Field Can Not Be Blank", Toast.LENGTH_LONG).show();
                }else {
                    viewModel.updateMessage(userName, userMobile, message);
                }
            }
        });
    }
}
