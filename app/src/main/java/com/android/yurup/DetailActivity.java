package com.android.yurup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.yurup.Fragments.HomeFragment;
import com.android.yurup.Fragments.ProfileFragment;
import com.android.yurup.Models.Challenge;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;


public class DetailActivity extends AppCompatActivity {
    public static final String TAG = "detailActivity";
    public static final String KEY_ITEM_TEXT = "challenge_text";
    public static final String KEY_ITEM_POSITION = "challenge_position";
    public static final int EDIT_TEXT_CODE = 29;
    public static final String KEY_TITLE = "challenge_title";
    public static final String KEY_DESCRIPTION = "challenge_description";
    public static final String KEY_START = "challenge_start";
    public static final String KEY_END = "challenge_end";
    public static final String KEY_ID = "challenge_objectID";
    public static final int REQUESTCODE = 100;

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
        tvStatus = findViewById(R.id.tvStatus);



        this.challenge = Parcels.unwrap(getIntent().getParcelableExtra("challenge"));
        tvTitle.setText(challenge.getTitle());
        tvDescription.setText(challenge.getDescription());
        tvCode.setText(challenge.getJoinCode());
        tvStartDate.setText(challenge.getStart());
        tvEndDate.setText(challenge.getEnd());
        tvStatus.setText(challenge.getHostStatus());




/*
        ParseUser host = challenge.getCreator();
        ParseFile image= host.getImage();
        if(image != null) {
            Glide.with(this).load(image.getUrl()).into(ivHost);
        } */


//        fabStatus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fabStatus.setImageResource(R.drawable.ic_hourglass_top_24);
////                Toast.makeText(DetailActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//
//            }
//
//        });

        bottomNavigationView = findViewById(R.id.bottomNavigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                activity_edit.putExtra(KEY_ID, DetailActivity.this.challenge.getObjectId());

                startActivityForResult(activity_edit,REQUESTCODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            ParseQuery<Challenge> query = ParseQuery.getQuery(Challenge.class);
            //fetching the updated challenge from api
            query.getInBackground(challenge.getObjectId(), new GetCallback<Challenge>() {
                // showing the updated challenge on this screen
                public void done(Challenge challenge, ParseException e) {
                    if (e == null) {
                        Log.e(TAG, challenge + "challenge");
                        tvTitle.setText(challenge.getTitle());
                        tvDescription.setText(challenge.getDescription());
                        tvCode.setText(challenge.getJoinCode());
                        tvStartDate.setText(challenge.getStart());
                        tvEndDate.setText(challenge.getEnd());
                    } else {

                        Log.e(TAG,"did not update");

                    }
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


}