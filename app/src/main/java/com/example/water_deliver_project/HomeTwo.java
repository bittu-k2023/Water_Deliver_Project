package com.example.water_deliver_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeTwo extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    private NavigationView navigation;
    private BottomNavigationView mybottomnavigation;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        navigation = (NavigationView) findViewById(R.id.navigation);
        mynavigation();
        mybottomnavigation = (BottomNavigationView) findViewById(R.id.mybottomnavigation);
        bottomnavi();
    }

    private void bottomnavi() {
        mybottomnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(HomeTwo.this, "Home", Toast.LENGTH_SHORT).show();
                        return true;
                    case  R.id.category:
                        Toast.makeText(HomeTwo.this, "Category", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.cart:
                        Toast.makeText(HomeTwo.this, "Cart", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.account:
                        Toast.makeText(HomeTwo.this, "Account", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    private void mynavigation() {

        toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable= ResourcesCompat.getDrawable(getResources(),R.drawable.burger,getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerVisible(GravityCompat.START))
                {
                    drawer.closeDrawer(GravityCompat.START);
                }
                else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.about:
                        Toast.makeText(HomeTwo.this, "about clicked", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.send_feedback:Toast.makeText(HomeTwo.this, "send feedback clicked", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.notification:Toast.makeText(HomeTwo.this, "Notification clicked", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.send_query:Toast.makeText(HomeTwo.this, "send query clicked", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.privacy_policy:Toast.makeText(HomeTwo.this, "privacy policy clicked", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        Intent intent=new Intent(getApplicationContext(),WLogin.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
                return false;
            }
        });
    }
}