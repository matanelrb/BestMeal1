package com.example.user.bestmeal1;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    Button btn1,btn2;
    EditText et1,et2,et3,et4,et5;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEDB64D")));
        getSupportActionBar().setTitle("Register");

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        et3 = (EditText)findViewById(R.id.et3);
        et4 = (EditText)findViewById(R.id.et4);
        et5 = (EditText)findViewById(R.id.et5);


    }

    public void backToLogin(View view) {

        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }

    public void signUp(View view) {

        db = new DBHandler(this);

        String email = et1.getText().toString();
        if(checkEmail(email)){

            if(db.checkEmail(email)) {

                Customer customer = new Customer(
                        et1.getText().toString(),
                        et2.getText().toString(),
                        et3.getText().toString(),
                        et4.getText().toString(),
                        et5.getText().toString()
                );

                db.addCustomer(customer);
                Intent intent = new Intent(this, Login.class);
                intent.putExtra("SUCCESS",true);
                startActivity(intent);
            }
            else Snackbar.make(view,"The email that you are trying to use is already in use",Snackbar.LENGTH_LONG).setAction("Action",null).show();



        }
        else Snackbar.make(view,"The email that you are trying to use is invalid",Snackbar.LENGTH_LONG).setAction("Action",null).show();


    }

    public boolean checkEmail(String email){

        for(int i =0;i<email.length();i++){
            if(email.charAt(i)=='@')
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.home){

            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }
}
