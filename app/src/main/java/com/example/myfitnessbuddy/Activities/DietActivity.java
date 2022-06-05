package com.example.myfitnessbuddy.Activities;

import android.os.Bundle;

import com.example.myfitnessbuddy.DietPlan;
import com.example.myfitnessbuddy.R;
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
import java.util.Random;

public class DietActivity extends AppCompatActivity {

    public DietActivity() {
        try
        {
//            if (!User.currentUser().hasDietPlan())
            {
                DietPlan plan = new DietPlan();
                plan.prepareWeek();
//                User.currentUser().setDietPlan(plan);
            }
        }
        catch (Exception ex)
        {
            Log.d("DIET", "general exception: " + ex);
        }
        finally {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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