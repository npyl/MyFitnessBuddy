package com.example.myfitnessbuddy;

public class User {
    private static User single_instance = null;

    private User()
    {
    }

    public static User currentUser()
    {
        if (single_instance == null)
            single_instance = new User();
        return single_instance;
    }

    public String email()
    {
        return "";
    }
}
