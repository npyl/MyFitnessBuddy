package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class Coach implements Planner {

    private String strongman[] = {
            "yok",
            "atlas stones",
    };

    private String biceps[] = {
            "bicep curls",
    };
    private String biceps_bw[] = {
            "bicep curls on bar"
    };

    private String triceps[] = {
            "tricep extensions",
    };
    private String triceps_bw[] = {
            "dips",
            "push ups"
    };

    private String legs[] = {
            "hamstrings"
    };
    private String legs_bw[] = {
            "lunges"
    };

    private String pecs[] = {
            "pec flies"
    };
    private String pecs_bw[] = {
            "archer push ups"
    };

    public void generateWeeklyExercisePlan()
    {

    }

    @Override
    public void prepareWeek() {

    }

    public Coach createCoachInstance()
    {
        return new Coach();
    }

    public void proposeChangesFor(ArrayList<String> mistakes)
    {}

    public Exercise proposeHarder(Exercise exercise)
    {
        return new Exercise();
    }

    public Exercise proposeEasier(Exercise exercise)
    {
        return new Exercise();
    }

    public void changeExerciseInPlan(Exercise ex, ExercisePlan plan)
    { }

    public ArrayList<String> calculatePossibleInjuries(ExercisePlan plan)
    {
        return new ArrayList<String>();
    }
}
