package com.android.yurup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.yurup.Fragments.HomeFragment;
import com.android.yurup.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JoinActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        setSupportActionBar((Toolbar) findViewById(R.id.Toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Toast.makeText(JoinActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(JoinActivity.this, "home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        Button btnJoin = findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JoinActivity.this, "Joined Challenge", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}