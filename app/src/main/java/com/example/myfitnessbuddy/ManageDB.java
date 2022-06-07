package com.example.myfitnessbuddy;

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

    ArrayList<JSONObject>       restaurantsMenus;

    // TODO: add
    ArrayList<Food>             meals;

    String[]                    professionalsList = {
            "George Bar",
            "John Gymnast",
            "Nick Pro",
    };

    private static ManageDB single_instance = null;

    public ManageDB()
    {
        //
        // Read User Data
        //
        try
        {
            //
            // Get List of users & Extract info for current user
            // (everything is contained in the UserData.json file)
            //

            ParseJSON pj = new ParseJSON("UserData.json", null);

            // getting json for each user
            JSONArray jsonsForUsers = pj.getJSONArray("users");

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

            //
            // Restaurant Menus
            //
            pj = new ParseJSON("RestaurantMenus.json", null);

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
