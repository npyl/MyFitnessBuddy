package com.example.myfitnessbuddy.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfitnessbuddy.ParseJSON;
import com.example.myfitnessbuddy.R;
import com.example.myfitnessbuddy.User;
import com.example.myfitnessbuddy.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_diet, R.id.nav_calorie_calc, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currentUserEmail = prefs.getString("signedUser", "");

        Log.d("MainActivity", "signedUser: " + currentUserEmail);

        if (currentUserEmail == null || currentUserEmail.isEmpty())
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else
        {
            try {
                ParseJSON pj = new ParseJSON("UserData.json", getApplicationContext());

                /* getting json for each user */
                JSONArray jsonsForUsers = pj.getListOfUsers();

                /*
                 * get current user info
                 */
                JSONObject o = null;

                for (int i = 0; i < jsonsForUsers.length(); i++)
                {
                    o = jsonsForUsers.getJSONObject(i);
                    String email = o.getString("email");

                    if (User.currentUser().getEmail() == email)
                    {
                        break;      // found it!
                    }
                }

                User.currentUser().setInfo(
                        o.getString("name"),
                        o.getString("email"),
                        o.getInt("age"),
                        o.getInt("height"),
                        o.getDouble("weight"),
                        o.getBoolean("male")
                );
            }
            catch (JSONException ex)
            {

            }
            finally
            {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // ON CLICK: this shows the Diet Activity on click
    public void showDietActivity(MenuItem item) {
        Log.d("dada", "sdadasasd");
        startActivity(new Intent(MainActivity.this, DietActivity.class));
    }

    public void logout(MenuItem item) {
        // remove this user from DB => logout
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().remove("signedUser").commit();

        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }
}
