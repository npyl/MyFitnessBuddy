package com.example.myfitnessbuddy;

import android.util.Log;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

    //
    //  (for fixes for mistakes)
    //
    private String mistakes_fixes[] = {
            ""
    };

    //
    //  (for proposing easier or harder alternatives)
    //
    private String exercises_alternatives[] = {
            "incline_push_ups push_ups decline_push_ups",
            "dips_on_bench dips korean_dips"
    };

    public void generateWeeklyExercisePlan()
    {

    }

    @Override
    public void prepareWeek()
    {

    }

    public Coach createCoachInstance()
    {
        return new Coach();
    }

    public void proposeChangesFor(ArrayList<String> mistakes)
    {

    }

    public Exercise proposeHarder(Exercise exercise)
    {
        String name = "";
        Exercise harder = new Exercise();

        for (int i = 0; i < exercises_alternatives.length; i++)
        {
            try
            {
                Scanner scanExercise = new Scanner(exercises_alternatives[i]);

                String regression = scanExercise.next();
                String current = scanExercise.next();
                String progression = scanExercise.next();

                if (current.equals(exercise.getName()))
                {
                    name = progression;
                }
            }
            catch (Exception ex)
            {
                Log.d("COACH", ex.getLocalizedMessage());
            }
        }

        harder.setName(name);
        harder.setReps(-1);     // we care for the name; not the number of reps

        return harder;
    }

    public Exercise proposeEasier(Exercise exercise)
    {
        String name = "";
        Exercise easier = new Exercise();

        for (int i = 0; i < exercises_alternatives.length; i++)
        {
            try
            {
                Scanner scanExercise = new Scanner(exercises_alternatives[i]);

                String regression = scanExercise.next();
                String current = scanExercise.next();
                String progression = scanExercise.next();

                if (current.equals(exercise.getName()))
                {
                    name = regression;
                }
            }
            catch (Exception ex)
            {
                Log.d("COACH", ex.getLocalizedMessage());
            }
        }

        easier.setName(name);
        easier.setReps(-1);     // we care for the name; not the number of reps

        return easier;
    }

    public void changeExerciseInPlan(Exercise ex, ExercisePlan plan)
    { }

    public ArrayList<String> calculatePossibleInjuries(ExercisePlan plan)
    {
        return new ArrayList<String>();
    }




    public void testProposeEasier()
    {
        Exercise current = new Exercise();
        current.setName("push_ups");

        Exercise easier = this.proposeEasier(current);
        Log.d("[COACH]", "Easier: " + easier.getName());
    }

    public void testProposeHarder()
    {
        Exercise current = new Exercise();
        current.setName("push_ups");

        Exercise harder = this.proposeHarder(current);
        Log.d("[COACH]", "Harder: " + harder.getName());
    }
}
