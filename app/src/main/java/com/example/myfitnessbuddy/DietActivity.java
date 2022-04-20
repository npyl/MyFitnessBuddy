package com.example.myfitnessbuddy;

import android.os.Bundle;

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

    //  string format:
    //  name:cal:pro:car:fat

    private String proteins[] = {
            "chicken:284:53.4:6.2",
    };

    private String carbs[] = {
            "apple:52:0:14:0",
            "banana:89:0:23:0"
    };

    private String fats[] = {
            "peanuts:0:0:0"
    };

    public DietActivity() {
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

                if (User.currentUser().email() == email)
                {
                    break;
                }
            }
        }
        catch (JSONException ex)
        {
            Log.d("DIET", "Got exception: " + ex);
        }
        finally {

        }
    }

    public void generateWeeklyDietPlan()
    {
        int proteinsArraySize = proteins.length;
        int carbsArraySize = carbs.length;

        int randomProtein = 0;
        int randomCarb = 0;

        Random rand = new Random();

        // generate foods for 7 days of a week
        for (int i = 0; i < 7; i++)
        {
            int newRandomProtein = 0;
            int newRandomCarb = 0;

            /* keep choosing a random protein until it's not the same as yesterday */
            do {
                newRandomProtein = rand.nextInt(proteinsArraySize);
            }
            while (newRandomProtein == randomProtein);

            /* (same for carb) */
            do {
                rand.nextInt(carbsArraySize);
            }
            while (newRandomCarb == randomCarb);

            randomProtein = newRandomProtein;
            randomCarb = newRandomCarb;

            String foods = proteins[randomProtein] + " with" + carbs[randomCarb];
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
