package com.example.myfitnessbuddy;

public class Food extends FoodOrExercise {
    private String name;
    private int calories, protein, carbs, fats;

    public Food(String name, int calories, int protein, int carbs, int fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }
}
