package com.example.myfitnessbuddy;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageDB {
    UserInfo                    userInfo;
    ArrayList<WorkoutLogEntry>  workoutLog;

    // TODO: add more
    String[]                    restaurantList = {
            "Thymari",
            "Xoiropoiito"
    };

    // TODO: create json
    JSONObject                  restaurantsMenus;

    // TODO: add
    ArrayList<Food>             meals;

    // TODO: add more
    String[]                    professionalsList = {
            "George",
            "John"
    };

    private static ManageDB single_instance = null;

    public ManageDB()
    {
        //
        // Read User Data
        //
        try
        {
            ParseJSON pj = new ParseJSON("UserData.json", null);

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
                    break;      // found it!
                }
            }

            User.currentUser().setInfo(
                    o.getString("name"),
                    o.getString("email"),
                    o.getInt("age"),
                    o.getInt("height"),
                    o.getDouble("weight"),
                    o.getBoolean("male")
            );

            pj = new ParseJSON("RestaurantMenus.json", null);
        }
        catch (Exception ex)
        {

        }
    }

    public static ManageDB manager()
    {
        if (single_instance == null)
            single_instance = new ManageDB();
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

    public ArrayList<String> downloadRestaurantsMenus() {
        // TODO: check if this works at all...
        return new ArrayList<String>(Arrays.<String>asList(String.valueOf(restaurantsMenus)));
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
