package com.example.user.bestmeal1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class displayDishes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dishes);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEDB64D")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBHandler db = new DBHandler(this);

        Intent intent = getIntent();

        ArrayList<Dish>dishes = db.getDishesByRestaurant(intent.getStringExtra("RESTAURANT_NAME"));

        dishAdapter adapter = new dishAdapter(this,dishes);

        ListView lv1 = (ListView)findViewById(R.id.lv1);
        lv1.setAdapter(adapter);

        adapter.setLoggedInName(intent.getStringExtra("LOGGED_IN_EMAIL"));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.home){

            Intent intent = new Intent(this,Main_Page.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
