package com.example.myfitnessbuddy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfitnessbuddy.Coach;
import com.example.myfitnessbuddy.ExercisePlan;
import com.example.myfitnessbuddy.ManageDB;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.StatisticsInfo;
import com.example.myfitnessbuddy.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                this.showNewWorkoutEntryPopup();
            }

            private void showNewWorkoutEntryPopup() {
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.new_workout_entry_popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(findViewById(android.R.id.content).getRootView(), Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
//                popupView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        popupWindow.dismiss();
//                        return true;
//                    }
//                });
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_workout_log, R.id.nav_diet, R.id.nav_coaching)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currentUserEmail = prefs.getString("signedUser", "");

        Log.d("MainActivity", "signedUser: " + currentUserEmail);

        if (currentUserEmail == null || currentUserEmail.isEmpty())
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else
        {
            Coach ch = new Coach();

            ch.testProposeHarder();
            ch.testProposeEasier();

            // Initialise ManageDB class throughout the whole app
            ManageDB dbManager = ManageDB.manager();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // ON CLICK: this shows the Diet Activity on click
    public void showDietActivity(MenuItem item) {
        startActivity(new Intent(MainActivity.this, DietActivity.class));
    }

    public void showCoachingActivity(MenuItem item) {
        startActivity(new Intent(MainActivity.this, CoachingActivity.class));
    }

    public void showWorkoutLog(MenuItem item) {
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    public void logout(MenuItem item) {
        // remove this user from DB => logout
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().remove("signedUser").commit();

        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }


    //
    //  Workout Log Functionality
    //
    public void predictTodaysWorkout(ExercisePlan ep) {}
    public void createWorkoutLogEntry(ArrayList<String> contents) {}
    public void showSummary() {}
    public void showStatisticsInfo(StatisticsInfo si) {}
    public void askForNewGoal() {}
    public void calculateNewGoals(StatisticsInfo si) {}
    public void calculateTimeUntilAchievingGoals(ArrayList<String> goals) {}
    public void showTimeUntilAchieved() {}
    public void askWhetherResultsArePleasing() {}
    public void showPossibleMistakes(ArrayList<String> mistakes) {}
    public void calculateFuturePersonalRecordsView() {}
    public void showListOfInjuries(ArrayList<String> injuries) {}
    public void replaceExercises() {}
}
