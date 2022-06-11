package com.example.myfitnessbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ManageDB {
    UserInfo                    userInfo;
    ArrayList<WorkoutLogEntry>  workoutLog;

    // TODO: add more
    String[]                    restaurantList = {
            "Thymari",
            "Xoiropoiito"
    };

    ArrayList<JSONObject>       restaurantsMenus;

    // TODO: add
    ArrayList<Food>             meals;

    String[]                    professionalsList = {
            "George Bar",
            "John Gymnast",
            "Nick Pro",
    };

    private static ManageDB single_instance = null;

    public ManageDB(Context ctx)
    {
        // create a workout log entry snapshot; initially empty
        workoutLog = new ArrayList<WorkoutLogEntry>();

        //
        // Read User Data
        //
        try
        {
            // ===================================================
            // Get List of users & Extract info for current user
            // (everything is contained in the UserData.json file)
            // ===================================================

            // -------------------------------------------------------------------
            // Basic User Data
            // -------------------------------------------------------------------

            // get current user email
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            String currentUserEmail = prefs.getString("signedUser", "");

            ParseJSON pj = new ParseJSON("UserData.json", ctx);

            // getting json for each user
            JSONArray jsonsForUsers = pj.getJSONArray("users");

            JSONObject o = null;

            for (int i = 0; i < jsonsForUsers.length(); i++)
            {
                o = jsonsForUsers.getJSONObject(i);
                String email = o.getString("email");

                Log.d("ManageDB", "Comparing: " + email + " with: " + currentUserEmail);

                if (currentUserEmail.equals(email))
                {
                    break;      // found it!
                }
            }

            Log.d("ManageDB", "Got user: "  + o.getString("name"));

            User.currentUser().setInfo(
                    o.getString("name"),
                    o.getString("email"),
                    o.getInt("age"),
                    o.getInt("height"),
                    o.getDouble("weight"),
                    o.getBoolean("male")
            );

            // -------------------------------------------------------------------

            // ------------------------------------------------------------------
            // Workout Log
            // ------------------------------------------------------------------
            JSONObject wl = o.getJSONObject("workout_log");

            if (wl.length() == 0)
            {
                Log.d("ManageDB", "error getting workout log data");
                return;
            }

            Log.d("ManageDB", "WorkoutLog keys: " + wl.length());

            //
            //  For every workout log entry:
            //      1. get workout log entry (as JSON object)
            //      2. extract notes
            //      3. extract exercises list and for each exercise create Exercise() object
            //      4. create Workout Log Entry
            //      5. add to workout log
            //
            Iterator<String> entry_dates = wl.keys();
            for (Iterator<String> it = entry_dates; it.hasNext(); )
            {
                String entry_date = it.next();

                Log.d("ManageDB", "For WorkoutLogEntry: " + entry_date);

                // get current workout log entry    (Step 1.)
                JSONObject current_workout_log_entry = wl.getJSONObject(entry_date);

                //
                //  Get Notes   (Step 2.)
                //
                String notes = current_workout_log_entry.getString("notes");

                Log.d("ManageDB", "Got notes: " + notes);

                //
                //  Get Exercises with their sets & reps    (Step 3.)
                //

                // create exercises list
                ArrayList<Exercise> exercisesList = new ArrayList<Exercise>();

                // get all keys; a.k.a. exercise names & notes
                Iterator<String> exerciseNames = current_workout_log_entry.keys();

                // foreach exercise we find; make sure its not user notes and extract info
                for (Iterator<String> it2 = exerciseNames; it2.hasNext(); )
                {
                    String key = it2.next();

                    // ignore "notes"
                    if (key.equals("notes"))
                        continue;

                    JSONObject exerciseJSON = current_workout_log_entry.getJSONObject(key);

                    // exercise data
                    String exerciseName = key;
                    int sets = exerciseJSON.getInt("sets");
                    int reps = exerciseJSON.getInt("reps");

                    // create exercise object
                    Exercise currentExercise = new Exercise();
                    currentExercise.setName(exerciseName);
                    currentExercise.setSets(sets);
                    currentExercise.setReps(reps);

                    // add to our list
                    exercisesList.add(currentExercise);
                }

                //
                //  Setup new workout log entry (Step 4.)
                //

                // create new entry
                WorkoutLogEntry newEntry = new WorkoutLogEntry();

                // if our list is not empty, add to workout log entry (also add the notes)
                if (!exercisesList.isEmpty())
                {
                    newEntry.setExerciseList(exercisesList);
                    newEntry.setNotes(notes);
                    newEntry.setDate(entry_date);
                }

                //
                //  Add New Workout Log Entry to Workout Log
                //
                workoutLog.add(newEntry);
            }

            // ---------------------------------------------------------------------------

            // ---------------------------------------------------------------------------
            // Restaurant Menus
            // ---------------------------------------------------------------------------
            pj = new ParseJSON("RestaurantMenus.json", ctx);

            // getting json for each restaurant
            JSONArray jsonsForRestaurants = pj.getJSONArray("restaurants");

            // clear json object o
            o = null;

            // foreach restaurant get menu
            for (int i = 0; i < jsonsForRestaurants.length(); i++)
            {
                o = jsonsForRestaurants.getJSONObject(i);

                // add to our list; this is a bit ugly but ok for now...
                restaurantsMenus.add(o);
            }

            // -------------------------------------------------------------------------

            // =========================================================================
        }
        catch (Exception ex)
        {
            Log.d("ManageDB", ex.getLocalizedMessage());
            Log.d("ManageDB", Log.getStackTraceString(ex));
        }
    }

    // this is to create the first static instance...
    public static ManageDB manager(Context ctx) throws Exception
    {
        if (single_instance != null)
            throw new Exception("ManageDB_initialisation_misuse: you should not call this method with an already initialised ManageDB instance!");

        single_instance = new ManageDB(ctx);
        return single_instance;
    }

    // this is to return an already initialised static instance...
    public static ManageDB manager() throws Exception {
        if (single_instance == null)
            throw new Exception("ManageDB_initialisation_misuse: you cannot call this method without having initialised ManageDB");
        return single_instance;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void addWorkoutLogEntry(WorkoutLogEntry entry)
    {
        workoutLog.add(entry);
    }

    public ArrayList<WorkoutLogEntry> getWorkoutLog() {
        return workoutLog;
    }

    public void updateDietPlan(DietPlan dp)
    {
        userInfo.setDietPlan(dp);
    }

    public void updateExercisesPlan(ExercisePlan ep)
    {
        userInfo.setExercisePlan(ep);
    }

    public ArrayList<String> downloadRestaurantList() {
        return new ArrayList<String>(Arrays.asList(restaurantList));
    }

    public ArrayList<JSONObject> downloadRestaurantsMenus() {
        return restaurantsMenus;
    }

    public DietPlan getDietPlan() {
        return userInfo.getDietPlan();
    }

    public ExercisePlan getExercisePlan() {
        return userInfo.getExercisePlan();
    }

    public void updateMeal(Food meal)
    {}

    public ArrayList<String> getProfessionalsList() {
        return new ArrayList<String>(Arrays.asList(professionalsList));
    }
}
