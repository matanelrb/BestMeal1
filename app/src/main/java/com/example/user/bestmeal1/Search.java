package com.example.user.bestmeal1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText et1;
    TextView tv1;
    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et1 = (EditText)findViewById(R.id.et1);
        tv1 = (TextView)findViewById(R.id.tv1);
        lv1 = (ListView)findViewById(R.id.lv1);
    }

    public void Search(View view) {

        DBHandler db = new DBHandler(this);
        Restaurant restaurant = db.getRestaurant(et1.getText().toString());

        ArrayList<Restaurant>restaurants = new ArrayList<>();
        restaurants.add(restaurant);

        restaurantAdapter adapter = new restaurantAdapter(this,restaurants);
        lv1.setAdapter(adapter);

    }
}
