package com.example.myfitnessbuddy;

import android.util.Log;

import java.util.Map;

public class LoginAndSignUp {
    static String db[] = {
            "nikos@gmail.com:123",
            "tasos@gmail.com:456",
            "vaggos@gmail.com:789",
            "mitsos@gmail.com:100",
    };

    //
    // Returns true if login was successful
    //
    public static Boolean login(String email, String password)
    {
        Log.d("LOGIN", "got email: " + email + " and password: " + password);

        for (int i = 0; i < db.length; i++)
        {
            String credentials[] = db[i].split(":");
            if (credentials.length != 2)
                return false;

            String currentEmail = credentials[0];
            String currentPassw = credentials[1];

            if (currentEmail.isEmpty() || currentPassw.isEmpty())
                return false;

            if (currentEmail.contentEquals(email) && currentPassw.contentEquals(password))
                return true;
        }

        // TODO: exception

        return false;
    }
}