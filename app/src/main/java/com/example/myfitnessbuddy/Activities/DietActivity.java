package com.example.myfitnessbuddy.Activities;

import android.os.Bundle;

import com.example.myfitnessbuddy.DietPlanner;
import com.example.myfitnessbuddy.Food;
import com.example.myfitnessbuddy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {

    public void showIngredients(ArrayList<String> ingredients) {}
    public void showFoodSummary(Food food) {}
    public void showGoalComparison(String goal) {}
    public void showUnnecessaryIngredients() {}

    public DietActivity() {
        try
        {
//            if (!User.currentUser().hasDietPlan())
            {
                DietPlanner planner = new DietPlanner();
                planner.prepareWeek();
//                User.currentUser().setDietPlan(plan);
            }
        }
        catch (Exception ex)
        {
            Log.d("DIET", "general exception: " + ex);
        }
        finally {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_activity);
    }
}