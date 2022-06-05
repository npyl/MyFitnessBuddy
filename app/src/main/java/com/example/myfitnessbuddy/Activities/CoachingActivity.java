package com.example.myfitnessbuddy.Activities;

import android.os.Bundle;

import com.example.myfitnessbuddy.ParseJSON;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoachingActivity extends AppCompatActivity {

    public CoachingActivity() {
        try
        {
            ParseJSON pj = new ParseJSON("UserData.json", getApplicationContext());

            /* getting json for each user */
            JSONArray jsonsForUsers = pj.getListOfUsers();

            /*
             * get current user info
             */
            JSONObject o = null;

            for (int i = 0; i < jsonsForUsers.length(); i++)
            {
                o = jsonsForUsers.getJSONObject(i);
                String email = o.getString("email");

                if (User.currentUser().getEmail() == email)
                {
                    break;
                }
            }
        }
        catch (JSONException ex)
        {
            Log.d("COACHING", "Got exception: " + ex);
        }
        finally {

        }
    }

    public void generateWeeklyExercisePlan()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coaching_activity);
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
