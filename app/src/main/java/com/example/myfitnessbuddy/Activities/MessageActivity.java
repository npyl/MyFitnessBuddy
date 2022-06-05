package com.example.myfitnessbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myfitnessbuddy.R;

public class MessageActivity extends AppCompatActivity {

    public MessageActivity createMessageActivity()
    {
        return new MessageActivity();
    }

    public String sendMessage(String msg)
    {
        return "";  // reply
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
}
