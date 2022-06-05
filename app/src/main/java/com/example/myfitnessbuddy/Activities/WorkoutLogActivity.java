package com.example.myfitnessbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnessbuddy.Exercise;
import com.example.myfitnessbuddy.ExercisePlan;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.StatisticsInfo;

import android.os.Bundle;

import java.util.ArrayList;

public class WorkoutLogActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);
    }
}
