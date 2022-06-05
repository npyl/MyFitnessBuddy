package com.example.myfitnessbuddy;

public class Order {
    Food meal;
    int payment;
    String restaurant;

    public Food getMeal() {
        return meal;
    }

    public void setMeal(Food meal) {
        this.meal = meal;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
