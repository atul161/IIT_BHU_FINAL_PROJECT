package com.root.atul.loginpage4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AfterLogin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if(savedInstanceState==null) {
           getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new FragmentProfile()).commit();
        }

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ImportantData.CONTACT_NO=bundle.getString("Cno");
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.after_login, menu);
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

        Fragment fm=null;
        if (id == R.id.nav_flood) {
            fm=new FragmentFlood();


            // Handle the camera action
        } else if (id == R.id.nav_earthquakes) {
            fm=new FragmentEarthQuakes();

        } else if (id == R.id.nav_tornadoes) {
            fm=new Tornadoes();

        } else if (id == R.id.nav_drought) {
            fm=new FragmentDrought();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_profile) {
            fm=new FragmentProfile();

        }
        else if(id==R.id.nav_logout)
        {
            ImportantData.BUTTON_VISIBILITY=1;
            ImportantData.CONTACT_NO="0";
          finish();
        }
        else if (id == R.id.nav_effectedarea) {


        }
        else if(id==R.id.nav_donatesociety)
        {
              fm=new FragmentDonateSociety();
        }
        else if(id==R.id.nav_pullrequest)
        {
            fm=new PullRequest();
        }
        else if (id==R.id.nav_messages)
        {

        }
        if (fm != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fm);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
