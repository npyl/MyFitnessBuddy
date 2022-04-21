package com.example.myfitnessbuddy;

//
//  The user class can also work as a singleton
//

public class User {
    private static User single_instance = null;

    private String name, email;
    private int age, height;
    private double weight;
    private boolean male;

    public User()
    {
        if (single_instance == null)
            single_instance = new User();
    }

    public static User currentUser()
    {
        return single_instance;
    }

    // one big setter
    public void setInfo(String name, String email, int age, int height, double weight, boolean male) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.male = male;
    }

    // accessors
    public String getName()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public int getAge()
    {
        return this.age;
    }
    public int getHeight()
    {
        return this.height;
    }
    public double getWeight()
    {
        return this.weight;
    }
    public boolean isMale()
    {
        return this.male;
    }
}
