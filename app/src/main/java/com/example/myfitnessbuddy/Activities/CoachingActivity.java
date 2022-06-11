package com.example.myfitnessbuddy.Activities;

import android.os.Bundle;

import com.example.myfitnessbuddy.ExercisePlan;
import com.example.myfitnessbuddy.ParseJSON;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.User;
import com.example.myfitnessbuddy.UserInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoachingActivity extends AppCompatActivity {

    ArrayList<String> exercisesList;

    public void showExercisesList() {}
    public void showVideo(String video) {}
    public void showProfessionalsList(ArrayList<String> list) {}
    public void sendUserData(UserInfo userData) {}
    public void showReply(String reply) {}
    public void calculateDifferencies(ExercisePlan ep, ExercisePlan ep2) {}

    public CoachingActivity() {

    }

    public void generateWeeklyExercisePlan()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coaching_activity);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
