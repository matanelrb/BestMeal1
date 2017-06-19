package com.example.user.bestmeal1;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btn1;
    EditText et1,et2;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn1 = (Button)findViewById(R.id.btn1);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        DBHandler db = new DBHandler(this);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFEDB64D")));
        getSupportActionBar().setTitle("Login");

    }


    public void login(View view) {

        db = new DBHandler(this);



        Intent intent = new Intent(this,Main_Page.class);
        if(db.checkLogin(et1.getText().toString(),et2.getText().toString())){
            intent.putExtra("LOGGED_IN_NAME",db.getCustomer(et1.getText().toString()).getFirst_name());
            intent.putExtra("LOGGED_IN_EMAIL",et1.getText().toString());
            startActivity(intent);
        }
        else{

            Snackbar.make(view,"The username or the password is incorrect",Snackbar.LENGTH_LONG).setAction("Action",null).show();

        }






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.btn1){

            Intent intent = new Intent(this,Register.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public void register(View view) {

        Intent intent = new Intent(this,Register.class);
        startActivity(intent);

    }
}
