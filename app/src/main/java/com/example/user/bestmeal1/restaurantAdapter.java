package com.example.user.bestmeal1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Matanel on 6/17/2017.
 */

public class restaurantAdapter extends ArrayAdapter<Restaurant> {


    public restaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context,0, restaurants);

    }

    public String loggedInName="";

    public void setLoggedInName(String name){loggedInName=name;}



    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final Restaurant restaurant = getItem(position);




        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_page_listview,parent,false);

        }

        TextView tvName = (TextView)convertView.findViewById(R.id.r_name);
        TextView tvPhoneNumber = (TextView)convertView.findViewById(R.id.number);

        tvName.setText(restaurant.getR_name());
        tvPhoneNumber.setText(restaurant.getR_phone_number());

        Button btn1 = (Button)convertView.findViewById(R.id.btn1);
        Button btnMenu = (Button)convertView.findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),displayDishes.class);
                intent.putExtra("RESTAURANT_NAME",restaurant.getR_name());
                intent.putExtra("LOGGED_IN_EMAIL",loggedInName);

                getContext().startActivity(intent);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        return convertView;

    }
}
