package com.example.myfitnessbuddy;

import java.util.ArrayList;
import java.util.Random;

public class DietPlan implements Planner {

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

    public DietPlan() {
        super();
    }

    @Override
    public void prepareWeek()
    {
        int proteinsArraySize = proteins.length;
        int carbsArraySize = carbs.length;

        int randomProtein = 0;
        int randomCarb = 0;

        Random rand = new Random();

        // generate foods for 7 days of a week
        for (int i = 0; i < 7; i++)
        {
            int newRandomProtein = 0;
            int newRandomCarb = 0;

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

            String breakfast_name = proteins[randomProtein] + " with" + carbs[randomCarb];

            // TODO: implement numbers here
            Food breakfast = new Food(breakfast_name, 0, 0, 0, 0);
            Food lunch = new Food("", 0, 0, 0, 0);
            Food dinner = new Food("", 0, 0, 0, 0);

            Day d = new Day();
            d.addFood(breakfast);
            d.addFood(lunch);
            d.addFood(dinner);
        }
    }
}
