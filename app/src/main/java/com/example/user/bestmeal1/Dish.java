package com.example.user.bestmeal1;

/**
 * Created by user on 17/05/2017.
 */
public class Dish {

    private String dish_name;
    private String restaurant_name;
    private String dish_type;
    private int dish_price;
    private int dish_rating;
    private int no_o_dish_ratings;
    private String[] customers_that_rated;

    public Dish(){}

    public Dish(String dish_name,String restaurant_name, String dish_type, int dish_price, int dish_rating, int no_o_dish_ratings,String[] customers_that_rated) {

        this.dish_name = dish_name;
        this.restaurant_name = restaurant_name;
        this.dish_type = dish_type;
        this.dish_price = dish_price;
        this.dish_rating = dish_rating;
        this.no_o_dish_ratings = no_o_dish_ratings;
        this.customers_that_rated = customers_that_rated;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getDish_type() {
        return dish_type;
    }

    public void setDish_type(String dish_type) {
        this.dish_type = dish_type;
    }

    public int getDish_price() {
        return dish_price;
    }

    public void setDish_price(int dish_price) {
        this.dish_price = dish_price;
    }

    public int getDish_rating() {
        return dish_rating;
    }

    public void setDish_rating(int dish_rating) {
        this.dish_rating = dish_rating;
    }

    public int getNo_o_dish_ratings() {
        return no_o_dish_ratings;
    }

    public void setNo_o_dish_ratings(int no_o_dish_ratings) {
        this.no_o_dish_ratings = no_o_dish_ratings;
    }

    public String[] getCustomers_that_rated() {
        return customers_that_rated;
    }

    public void setCustomers_that_rated(String[] customers_that_rated) {
        this.customers_that_rated = customers_that_rated;
    }
}
