package com.example.myfitnessbuddy;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DietPlanner implements Planner {

    ArrayList<String> availableIngredients;

    public ArrayList<String> getAvailableIngredients() {
        return availableIngredients;
    }
    public DietPlanner createDietPlannerInstance()
    {
        return new DietPlanner();
    }

    //  string format:
    //  name:cal:pro:car:fat

    private String proteins[] = {
            "chicken:284:53.4:6.2",
    };

    private String carbs[] = {
            "apple:52:0:14:0",
            "banana:89:0:23:0"
    };

    private String fats[] = {
            "peanuts:0:0:0"
    };

    private int randomProtein = 0;
    private int randomCarb = 0;

    public DietPlanner() {
        super();
    }

    private String generateRandomFood()
    {
        int proteinsArraySize = proteins.length;
        int carbsArraySize = carbs.length;

        int newRandomProtein = 0;
        int newRandomCarb = 0;

        Random rand = new Random();

        /* keep choosing a random protein until it's not the same as yesterday */
        do {
            newRandomProtein = rand.nextInt(proteinsArraySize);
        }
        while (newRandomProtein == randomProtein);

        /* (same for carb) */
        do {
            rand.nextInt(carbsArraySize);
        }
        while (newRandomCarb == randomCarb);

        randomProtein = newRandomProtein;
        randomCarb = newRandomCarb;

        // format:
        // protein_name:prot:carb:fat;carb_name:prot:carb:fat
        return proteins[randomProtein] + ";" + carbs[randomCarb];
    }

    //
    //  foodForDescription():
    //
    //  Takes a food description as a string, in the format that this.generateRandomFood() returns it
    //      and create a Food class instance representation
    //
    private Food foodForDescription(String foodDescription)
    {
        // TODO: implement numbers here
        String  name = null;
        int     calories = 0,
                proteins = 0,
                carbs = 0,
                fats = 0;

        Scanner scanFoodDescription = new Scanner(foodDescription);

        name = scanFoodDescription.next();
        calories = scanFoodDescription.nextInt();
        proteins = scanFoodDescription.nextInt();
        fats = scanFoodDescription.nextInt();

        name += " with " + scanFoodDescription.next();
        calories += scanFoodDescription.nextInt();
        proteins += scanFoodDescription.nextInt();
        fats += scanFoodDescription.nextInt();

        Log.d("FOOD", name + " cals: " + calories);

        return new Food(name, calories, proteins, carbs, fats);
    }

    @Override
    public void prepareWeek()
    {

    }
}
