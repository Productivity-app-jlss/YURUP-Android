package com.android.yurup;

import android.content.Intent;
import android.os.Bundle;

import com.android.yurup.Fragments.HomeFragment;
import com.android.yurup.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.view.ViewAnimationUtils;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton fab_main, fab_create, fab_join;
    boolean isRotate = false;
    public static final String TAG = "MainActivity";
    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab_main = findViewById(R.id.fab_main);
        final FloatingActionButton fab_create = findViewById(R.id.fab_create);
        final FloatingActionButton fab_join = findViewById(R.id.fab_join);

        Animation.init(fab_create);
        Animation.init(fab_join);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Add challenge", Toast.LENGTH_SHORT).show();
                isRotate = Animation.rotateFab(view, !isRotate);
                if(isRotate){
                    Animation.showIn(fab_create);
                    Animation.showIn(fab_join);
                }else{
                    Animation.showOut(fab_create);
                    Animation.showOut(fab_join);
                }

            }
        });

        fab_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Create challenge", Toast.LENGTH_SHORT).show();
                Intent create_activity = new Intent(getApplicationContext(), createActivity.class);
                startActivity(create_activity);
            }
        });

        fab_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Join challenge", Toast.LENGTH_SHORT).show();
                Intent join_activity = new Intent(getApplicationContext(), joinActivity.class);
                startActivity(join_activity);
            }
    
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_profile:
                        Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);


//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}