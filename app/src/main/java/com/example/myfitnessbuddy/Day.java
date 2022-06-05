package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class Day {
    private ArrayList items;                        // items (can be either foods or exercises)
    private String name;                            // name of the day

    public Day(String nameOfDay) {
        this.name = nameOfDay;
        this.items = null;
    }

    public Day() {}

    public void addFood(Food f) {
        items.add(f);
    }
    public void addExercise(Exercise e) {
        items.add(e);
    }
}
