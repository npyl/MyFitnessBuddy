package com.example.myfitnessbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myfitnessbuddy.Food;
import com.example.myfitnessbuddy.R;

import java.util.ArrayList;

public class AFoodActivity extends AppCompatActivity {

    public void showRestaurantsList(ArrayList<String> restaurantsList) {}
    public void applyFilters(ArrayList<String> restauntList, ArrayList<String> filters) {}
    public void showRestaurantMenu(String restaurant) {}
    public void showMealSummary(Food meal) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_food);
    }
}
