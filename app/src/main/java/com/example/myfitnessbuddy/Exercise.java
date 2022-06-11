package com.example.myfitnessbuddy;

public class Exercise {
    String  name;
    int     sets;
    int     reps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getSets() { return sets; }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
