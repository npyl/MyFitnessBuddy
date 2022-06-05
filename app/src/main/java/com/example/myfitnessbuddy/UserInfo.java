package com.example.myfitnessbuddy;

public class UserInfo {
    ExercisePlan    exercisePlan;
    DietPlan        dietPlan;

    public ExercisePlan getExercisePlan() {
        return exercisePlan;
    }

    public void setExercisePlan(ExercisePlan exercisePlan) {
        this.exercisePlan = exercisePlan;
    }

    public DietPlan getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
    }
}
