package com.android.yurup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.yurup.Fragments.HomeFragment;
import com.android.yurup.Fragments.ProfileFragment;
import com.android.yurup.Models.Challenge;
import com.android.yurup.Models.User;
import com.android.yurup.challengeActivities.JoinActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.parse.ParseFile;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvDescription;
    TextView tvCode;
    TextView tvStartDate;
    TextView tvEndDate;
    ImageView ivHost;

    BottomNavigationView bottomNavigationView;

    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        tvCode = findViewById(R.id.tvCode);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        ivHost = findViewById(R.id.ivHost);

        Challenge challenge = Parcels.unwrap(getIntent().getParcelableExtra("challenge"));
        tvTitle.setText(challenge.getTitle());
        tvDescription.setText(challenge.getDescription());
        tvCode.setText(challenge.getJoinCode());
        tvStartDate.setText(challenge.getStart());
        tvEndDate.setText(challenge.getEnd());
/*
        ParseUser host = challenge.getCreator();
        ParseFile image= host(User).getImage();
        if(image != null) {
            Glide.with(this).load(image.getUrl()).into(ivHost);
        }
*/

        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Toast.makeText(DetailActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(DetailActivity.this, "home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });







    }
}