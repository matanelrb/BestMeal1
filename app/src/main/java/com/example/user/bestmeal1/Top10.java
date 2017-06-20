package com.example.user.bestmeal1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Top10 extends AppCompatActivity {

    TextView tv1;
    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        DBHandler db = new DBHandler(this);
        lv1 = (ListView)findViewById(R.id.lv1);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEDB64D")));


        Bundle extras = getIntent().getExtras();

        if(extras.getString("TOP10").equals("dishes")){


            ArrayList<Dish>dishes = db.getTop10Dishes();
            dishAdapter adapter = new dishAdapter(this,dishes);
            lv1.setAdapter(adapter);

            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.top10_header_layout,null);
            lv1.addHeaderView(view);
            tv1 = (TextView)view.findViewById(R.id.tv1);

            tv1.setText("The Top 10 Dishes:");
        }

    }


}
