package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class ManageDB {
    UserInfo                    userInfo;
    DietPlan                    dietPlan;
    ExercisePlan                exercisePlan;
    ArrayList<WorkoutLogEntry>  workoutLog;
    ArrayList<String>           restaurantList;
    ArrayList<String>           restaurantsMenus;
    ArrayList<Food>             meals;
    ArrayList<String>           professionalsList;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void addWorkoutLogEntry(WorkoutLogEntry entry)
    {}

    public ArrayList<WorkoutLogEntry> getWorkoutLog() {
        return workoutLog;
    }

    public void updateDietPlan(DietPlan dp)
    {}

    public void updateExercisesPlan(ExercisePlan ep)
    {}

    public ArrayList<String> downloadRestaurantList() {
        return restaurantList;
    }

    public ArrayList<String> downloadRestaurantsMenus() {
        return restaurantsMenus;
    }

    public DietPlan getDietPlan() {
        return dietPlan;
    }

    public ExercisePlan getExercisePlan() {
        return exercisePlan;
    }

    public void updateMeal(Food meal)
    {}

    public ArrayList<String> getProfessionalsList() {
        return professionalsList;
    }
}
