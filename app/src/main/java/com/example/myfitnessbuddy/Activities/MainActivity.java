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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfitnessbuddy.Coach;
import com.example.myfitnessbuddy.Exercise;
import com.example.myfitnessbuddy.ExercisePlan;
import com.example.myfitnessbuddy.ManageDB;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.Statistics;
import com.example.myfitnessbuddy.StatisticsInfo;
import com.example.myfitnessbuddy.WorkoutLogEntry;
import com.example.myfitnessbuddy.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    WorkoutLogEntry newEntry;           // created when using the New Entry Popup

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

            //
            //  (For the New WorkoutLog Entry popup)
            //
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

            try
            {
                // Initialise ManageDB class throughout the whole app
                ManageDB dbManager = ManageDB.manager(getApplicationContext());

                //
                //  Show Workout Log
                //
                ArrayList<WorkoutLogEntry> workoutLog = dbManager.getWorkoutLog();

                //
                // get the workout log text view (it belongs to different view so we need to do some work to get it)
                //
                TextView workoutLogTextView = this.findViewById(R.id.workoutLogTextView);
                workoutLogTextView.getText();

                for (int i = 0; i < workoutLog.size(); i++)
                {
                    // get current entry
                    WorkoutLogEntry currentEntry = workoutLog.get(i);

                    // get exercises list
                    ArrayList<Exercise> exercisesList = currentEntry.getExerciseList();

                    //
                    // print the entry
                    //
                    String text = (String) workoutLogTextView.getText();
                    text += currentEntry.getDate() + ":\n";

                    for (int j = 0; j < exercisesList.size(); j++)
                    {
                        Exercise currentExercise = exercisesList.get(j);

                        text += currentExercise.getName() + ":\n" +
                                "SETS: " + currentExercise.getSets() + "\n" +
                                "REPS: " + currentExercise.getReps() + "\n";
                    }

                    if (!currentEntry.getNotes().isEmpty())
                    {
                        text += "\nNotes: " + currentEntry.getNotes() + "\n\n";
                    }

                    workoutLogTextView.setText(text);
                }
            }
            catch (Exception ex)
            {
                Log.d("MainActivity", ex.getLocalizedMessage());
                Log.d("MainActivity", Log.getStackTraceString(ex));
            }
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

    public void showStatsPopup(View view)
    {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.workout_log_stats_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(findViewById(android.R.id.content).getRootView(), Gravity.CENTER, 0, 0);
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

    //
    //  (These are for popups)
    //

    public void saveNewWorkoutLogEntry(View view) {
        Log.d("MAIN", "save workout entry");

        // TODO: the following code does not work!

//        TextView exerciseTextBox = findViewById(R.id.exerciseTextBox);
//        TextView setsTextBox = findViewById(R.id.setsTextBox);
//        TextView repsTextBox = findViewById(R.id.repsTextBox);
//        TextView kgsTextBox = findViewById(R.id.kgsTextBox);
//
//        if (exerciseTextBox == null || setsTextBox == null || repsTextBox == null || kgsTextBox == null)
//            return;
//
//        Log.d("MAIN", "AT LEAST HERE!");
//
//        String exercise = exerciseTextBox.getText().toString();
//        String sets = setsTextBox.getText().toString();
//        String reps = repsTextBox.getText().toString();
//        String kgs = kgsTextBox.getText().toString();
//
//        if (
//                exercise.isEmpty()  ||
//                        sets.isEmpty()      ||
//                        reps.isEmpty()      ||
//                        kgs.isEmpty()
//        )
//        {
//            return;
//        }
//
//        Log.d("MAIN", "Got: " + exercise + " + " + sets);

        try
        {
            // So we just mimic the logic of creating a new entry
            String exercise = "pull_downs";
            String sets = "10";
            String reps = "10";
            String kgs = "60";

            String notes = "test test test!";

            // create format
            String exercise1 = exercise + " " + sets + " " + kgs;
            ArrayList<String> contents = new ArrayList<>();
            contents.add(exercise1);
            contents.add(notes);

            // create workout log entry
            newEntry = WorkoutLogEntry.createWorkoutLogEntry(contents);

            // add workout log entry
            ManageDB.manager().addWorkoutLogEntry(newEntry);

            Log.d("MainActivity", "Got: " + newEntry.getNotes());

            // TODO: implement
            showSummary();

            Statistics statistics = Statistics.createStatisticsInstance();
            StatisticsInfo si = statistics.getStatistics();

            // show statistics info
            // TODO: implement
            showStatisticsInfo(si);

            // TODO: implement the use case further with goals etc.
        }
        catch (Exception ex)
        {
            Log.d("MainActivity", ex.getLocalizedMessage());
            Log.d("MainActivity", Log.getStackTraceString(ex));
        }
    }

    public void closeNewWorkoutLogEntry(View view) {
    }
}