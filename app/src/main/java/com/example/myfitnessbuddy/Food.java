package com.example.myfitnessbuddy;

import java.util.ArrayList;

public class Food {
    private String name;
    private int calories, protein, carbs, fats;

    ArrayList<String> ingredients;

    public Food(String name, int calories, int protein, int carbs, int fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
