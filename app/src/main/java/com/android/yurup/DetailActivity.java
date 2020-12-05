package com.android.yurup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import static com.parse.Parse.getApplicationContext;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_ITEM_TEXT = "challenge_text";
    public static final String KEY_ITEM_POSITION = "challenge_position";
    public static final int EDIT_TEXT_CODE = 29;
    public static final String KEY_TITLE = "challenge_title";
    public static final String KEY_DESCRIPTION = "challenge_description";
    public static final String KEY_START = "challenge_start";
    public static final String KEY_END = "challenge_end";


    TextView tvTitle;
    TextView tvDescription;
    TextView tvCode;
    TextView tvStartDate;
    TextView tvEndDate;
    TextView tvStatus;
    ImageView ivHost;
    Button btnEdit;
    BottomNavigationView bottomNavigationView;
    Challenge challenge;

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
        btnEdit = findViewById(R.id.btnEdit);
        tvStatus =findViewById(R.id.tvStatus);

        this.challenge = Parcels.unwrap(getIntent().getParcelableExtra("challenge"));
        tvTitle.setText(challenge.getTitle());
        tvDescription.setText(challenge.getDescription());
        tvCode.setText(challenge.getJoinCode());
        tvStartDate.setText(challenge.getStart());
        tvEndDate.setText(challenge.getEnd());
        tvStatus.setText(challenge.getHostStatus());


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

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_edit = new Intent(getApplicationContext(), EditActivity.class);
                activity_edit.putExtra(KEY_TITLE, DetailActivity.this.challenge.getTitle());
                activity_edit.putExtra(KEY_START, DetailActivity.this.challenge.getStart());
                activity_edit.putExtra(KEY_END, DetailActivity.this.challenge.getEnd());
                activity_edit.putExtra(KEY_DESCRIPTION, DetailActivity.this.challenge.getDescription());

                startActivity(activity_edit);
            }
        });

    }



}