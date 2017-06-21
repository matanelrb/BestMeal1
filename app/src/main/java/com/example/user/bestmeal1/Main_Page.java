package com.example.user.bestmeal1;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Main_Page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tv1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);




        DBHandler db = new DBHandler(this);
        final ArrayList<Restaurant>restaurants = db.getAllRestaurants();

        final restaurantAdapter adapter = new restaurantAdapter(this,restaurants);

        ListView listView = (ListView)findViewById(R.id.lv1);

        listView.setAdapter(adapter);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_page_header_layout,null);
        listView.addHeaderView(view);
        tv1 = (TextView)view.findViewById(R.id.tv1);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#FFEDB64D"));
        if(savedInstanceState == null){

            Bundle extras = getIntent().getExtras();
            tv1.setText("A list of all restaurants that we have in our database:");
            adapter.setLoggedInName(extras.getString("LOGGED_IN_EMAIL"));
            toolbar.setSubtitle("Welcome "+extras.getString("LOGGED_IN_NAME").substring(0, 1).toUpperCase() + extras.getString("LOGGED_IN_NAME").substring(1));

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_find_a_restaurant) {

            Intent intent = new Intent(this,Search.class);
            startActivity(intent);

        }  else if (id == R.id.nav_top_ten_dishes) {

            Intent intent = new Intent(this,Top10.class);
            intent.putExtra("TOP10","dishes");
            Bundle extras = getIntent().getExtras();
            intent.putExtra("LOGGED_IN_NAME",extras.getString("LOGGED_IN_NAME"));
            intent.putExtra("LOGGED_IN_EMAIL",extras.getString("LOGGED_IN_EMAIL"));

            startActivity(intent);

        }else if(id == R.id.nav_cheap_dishes){

            Intent intent = new Intent(this,cheapestDishes.class);
            startActivity(intent);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
