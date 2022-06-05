package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class WorkoutLogEntry {
    String              notes;
    ArrayList<Exercise> exerciseList;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
