package com.example.user.bestmeal1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


// * Created by user on 17/05/2017.

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 2;


    // CUSTOMER
    public static final String TABLE_CUSTOMER = "customer";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String C_PHONE_NUMBER = "phone_number";

    //RESTAURANT
    public static final String TABLE_RESTAURANT = "restaurants";

    public static final String RESTAURANT_NAME = "name";
    public static final String RESTAURANT_ADDRESS = "address";
    public static final String R_PHONE_NUMBER = "phone_number";


    //DISH
    public static final String TABLE_DISH = "dishes";

    public static final String DISH_NAME = "dish_name";
    public static final String DISH_TYPE = "type";
    public static final String DISH_PRICE = "price";
    public static final String DISH_RATING = "rating";
    public static final String NO_OF_DISH_RATINGS = "number_of_ratings";
    public static final String CUSTOMERS_THAT_RATED = "customer_that_rated";

    //ORDER
  /*  public static final String TABLE_ORDER = "orders";

    public static final String ORDER_ID = "id";
*/

    public static final String[] CUSTOMER_COLUMNS = {KEY_EMAIL, KEY_PASSWORD, FIRST_NAME, LAST_NAME, C_PHONE_NUMBER};
    public static final String[] RESTAURANT_COLUMNS = {RESTAURANT_NAME, RESTAURANT_ADDRESS, R_PHONE_NUMBER};
    public static final String[] DISH_COLUMNS = {DISH_NAME,"restaurant_name", DISH_TYPE, DISH_PRICE, DISH_RATING, NO_OF_DISH_RATINGS,CUSTOMERS_THAT_RATED};
//    public static final String[] ORDER_COLUMNS = {ORDER_ID, KEY_EMAIL, RESTAURANT_NAME, DISH_NAME};


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String Create_T_Customer = "CREATE TABLE " + TABLE_CUSTOMER + "(" + KEY_EMAIL + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT," + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT," + C_PHONE_NUMBER + " TEXT);";
        String Create_T_Restaurant = "CREATE TABLE " + TABLE_RESTAURANT + "(" + RESTAURANT_NAME + " TEXT PRIMARY KEY," + RESTAURANT_ADDRESS + " TEXT," + R_PHONE_NUMBER + " TEXT);";
        String Create_T_Dish = "CREATE TABLE " + TABLE_DISH + "(" + DISH_NAME + " TEXT,"+"restaurant_name"+" TEXT," + DISH_TYPE + " TEXT," + DISH_PRICE + " TEXT," + DISH_RATING + " TEXT," + NO_OF_DISH_RATINGS + " TEXT,"+CUSTOMERS_THAT_RATED+" TEXT,PRIMARY KEY("+DISH_NAME+","+"restaurant_name"+"));";
       // String Create_T_Order = "CREATE TABLE " + TABLE_ORDER + "(" + ORDER_ID + " INTEGER," + KEY_EMAIL + " TEXT," + "restaurant_name" + " TEXT," + DISH_NAME + " TEXT,PRIMARY KEY("+ORDER_ID+","+KEY_EMAIL+","+"restaurant_name"+","+DISH_NAME+"));";

        db.execSQL(Create_T_Customer);
        db.execSQL(Create_T_Restaurant);
        db.execSQL(Create_T_Dish);
      //  db.execSQL(Create_T_Order);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISH);
        onCreate(db);
      //  db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
       // onCreate(db);


    }

    public void addCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, customer.getEmail());
        values.put(KEY_PASSWORD, customer.getPassword());
        values.put(FIRST_NAME, customer.getFirst_name());
        values.put(LAST_NAME, customer.getLast_name());
        values.put(C_PHONE_NUMBER, customer.getPhone_number());

        db.insert(TABLE_CUSTOMER, null, values);

        db.close();

    }

    public void addRestaurant(Restaurant restaurant) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESTAURANT_NAME, restaurant.getR_name());
        values.put(RESTAURANT_ADDRESS, restaurant.getR_address());
        values.put(R_PHONE_NUMBER, restaurant.getR_phone_number());

        db.insert(TABLE_RESTAURANT, null, values);

        db.close();

    }

    public void addDish(Dish dish) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISH_NAME, dish.getDish_name());
        values.put(DISH_TYPE, dish.getDish_type());
        values.put(DISH_PRICE, dish.getDish_type());
        values.put(DISH_RATING, dish.getDish_rating());
        values.put(NO_OF_DISH_RATINGS, dish.getNo_o_dish_ratings());
        values.put(CUSTOMERS_THAT_RATED, convertArrayToString(dish.getCustomers_that_rated()));

        db.insert(TABLE_DISH, null, values);

        db.close();

    }

  /*  public void addOrder(Order order) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, order.getEmail());
        values.put(RESTAURANT_NAME, order.getRestaurantName());
        values.put(DISH_NAME, order.getDishName());

        db.insert(TABLE_ORDER, null, values);

        db.close();

    }*/

    public Customer getCustomer(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CUSTOMER, CUSTOMER_COLUMNS, KEY_EMAIL + " = ?", new String[]{email}, null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

        }

        Customer customer = new Customer(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));

        return customer;

    }

    public Restaurant getRestaurant(String resName) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RESTAURANT, RESTAURANT_COLUMNS, RESTAURANT_NAME + " = ?", new String[]{resName}, null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

        }

        Restaurant restaurant = new Restaurant(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2));

        return restaurant;

    }

    public Dish getDish(String dishName) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DISH, DISH_COLUMNS, DISH_NAME + " = ?", new String[]{dishName}, null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

        }

        Dish dish = new Dish(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)),
                convertStringToArray(cursor.getString(6)));

        return dish;
    }

  /*  public Order getOrder(String orderID) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDER, ORDER_COLUMNS, ORDER_ID + " = ?", new String[]{orderID}, null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();

        }

        Order order = new Order(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return order;

    }
*/
    public List<String> getAllEmails() {

        List<String> emails = new LinkedList<String>();

        String query = "SELECT * FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String email = null;
        if (cursor.moveToFirst()) {

            do {
                email = cursor.getString(0);
                emails.add(email);

            } while (cursor.moveToNext());

        }

        return emails;

    }

    public boolean checkEmail(String email){

        String[]emails = new String[getAllEmails().size()];
        emails = getAllEmails().toArray(emails);

        return !Arrays.asList(emails).contains(email);

    }

    public boolean checkLogin(String username, String password){

        List<String>emails = getAllEmails();
        for(String email : emails){
            if(email.equals(username)){
                if(getCustomer(email).getPassword().equals(password))
                    return true;
            }
        }
        return false;

    }

    public ArrayList<Restaurant> getAllRestaurants(){

        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

        String query = "SELECT * FROM "+TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){

            do{

                Restaurant restaurant = new Restaurant(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                restaurants.add(restaurant);

            }while (cursor.moveToNext());

        }
        return restaurants;
    }

    public ArrayList<Dish> getDishByRestaurant(String rName){

        ArrayList<Dish>dishes = new ArrayList<Dish>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DISH,DISH_COLUMNS,"restaurant_name"+ " =?",new String[]{rName},null,null,null,null);

        if(cursor.moveToFirst()){

            do{

                Dish dish = new Dish(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        convertStringToArray(cursor.getString(6)));
                dishes.add(dish);
            }while (cursor.moveToNext());

        }
        return dishes;

    }

    public void rateDish(Dish dish,int rating,String loggedInName){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

            dish.setNo_o_dish_ratings(dish.getNo_o_dish_ratings() + 1);
            int newRating = (dish.getDish_rating() * (dish.getNo_o_dish_ratings() - 1) + rating) / dish.getNo_o_dish_ratings();
            String stringArray = convertArrayToString(dish.getCustomers_that_rated());
            dish.setDish_rating(newRating);
            stringArray = stringArray+strSeparator+loggedInName;
            dish.setCustomers_that_rated(convertStringToArray(stringArray));

            values.put(DISH_RATING, newRating);
            values.put(NO_OF_DISH_RATINGS, dish.getNo_o_dish_ratings());
            values.put(CUSTOMERS_THAT_RATED,stringArray);

            db.update(TABLE_DISH, values, DISH_NAME + " =? AND restaurant_name =?", new String[]{dish.getDish_name(), dish.getRestaurant_name()});

    }


    public static String strSeparator = "__,__";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }

}
