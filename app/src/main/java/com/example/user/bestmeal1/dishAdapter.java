package com.example.user.bestmeal1;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Matanel on 6/17/2017.
 */

public class dishAdapter extends ArrayAdapter<Dish> {

    DBHandler db;
    public String loggedInName;

    public dishAdapter(Context context, ArrayList<Dish> restaurants) {
        super(context,0, restaurants);
    }

    public void setLoggedInName(String name){

        loggedInName = name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final Dish dish = getItem(position);



        db = new DBHandler(getContext());

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dish_listview_design,parent,false);

        }

        TextView tvName = (TextView)convertView.findViewById(R.id.tvName);
        TextView tvPrice = (TextView)convertView.findViewById(R.id.tvPrice);
        TextView tvType = (TextView)convertView.findViewById(R.id.tvtype);
        TextView tvRating = (TextView)convertView.findViewById(R.id.tvrating);
        TextView tvNoOfRatings = (TextView)convertView.findViewById(R.id.tvNoOfRatings);

        tvName.setText(String.valueOf(position+1)+"."+dish.getDish_name());
        tvPrice.setText("Price:"+String.valueOf(dish.getDish_price())+"₪");
        tvType.setText(dish.getDish_type());
        tvRating.setText("Rating:"+String.valueOf(dish.getDish_rating()));
        tvNoOfRatings.setText("Number of Ratings:"+String.valueOf(dish.getNo_o_dish_ratings()));

        Button btnRating = (Button)convertView.findViewById(R.id.btnRating);
        final EditText etRating = (EditText)convertView.findViewById(R.id.etRating);
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Integer.parseInt(etRating.getText().toString())<11 && Integer.parseInt(etRating.getText().toString())>-1){

                    if(!(Arrays.asList(dish.getCustomers_that_rated()).contains(loggedInName))) {

                        db.rateDish(dish, Integer.parseInt(etRating.getText().toString()), loggedInName);
                        Snackbar.make(view, "You have successfully rated the dish", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        notifyDataSetChanged();
                    }
                    else{
                        Snackbar.make(view,"You have already rated this dish",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    }
                }
                else {

                    Snackbar.make(view,"The rating is invalid",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                }


            }
        });

        return convertView;

    }
}
