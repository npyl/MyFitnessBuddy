package com.example.myfitnessbuddy;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutLogEntry {
    String              notes;
    String              date;
    ArrayList<Exercise> exerciseList;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public static WorkoutLogEntry createWorkoutLogEntry(ArrayList<String> contents)
    {
        //
        //  we take the contents and foreach string (that isn't the last one):
        // and create a workout log entry
        //
        WorkoutLogEntry newEntry = new WorkoutLogEntry();

        ArrayList<Exercise> exercisesList = new ArrayList<>();

        // set each exercise
        for (int i = 0; i < contents.size() - 1; i++)
        {
            String current_string = contents.get(i);

            Scanner scanExercise = new Scanner(current_string);

            // get exercise data from current string
            String name = scanExercise.next();
            int sets = Integer.parseInt(scanExercise.next());
            int reps = Integer.parseInt(scanExercise.next());
//            int kgs = Integer.parseInt(scanExercise.next());

            // create exercise from current string
            Exercise currentExercise = new Exercise();
            currentExercise.setName(name);
            currentExercise.setSets(sets);
            currentExercise.setReps(reps);

            // add to exercises list
            exercisesList.add(currentExercise);
        }

        // add exercises list
        newEntry.setExerciseList(exercisesList);

        // set notes
        String notes = contents.get(contents.size() - 1);
        newEntry.setNotes(notes);

        return newEntry;
    }
}
