package com.android.yurup.challengeActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.yurup.Fragments.HomeFragment;
import com.android.yurup.Fragments.ProfileFragment;
import com.android.yurup.Models.Challenge;
import com.android.yurup.Models.Participant;
import com.android.yurup.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class JoinActivity extends AppCompatActivity {

    public static final String TAG = "JoinActivity";
    EditText etJoinCode;

    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    protected List<Challenge> allChallenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etJoinCode = findViewById(R.id.etJoinCode);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

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
                String joinCode = etJoinCode.getText().toString();
                Challenge jChallenge = getJoinedChallenge(joinCode);
                ParseUser currUser = ParseUser.getCurrentUser();
                String status = "PENDING";
                joinChallenge(jChallenge, currUser, status);
                Toast.makeText(JoinActivity.this, "Joined Challenge", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void joinChallenge(ParseObject challenge, ParseUser currentUser, String status) {
        Participant participant = new Participant();
        participant.setChallenge(challenge);
        participant.setStatus(status);
        participant.setParticipant(currentUser);
        participant.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Join failed due to error: ", e);
                    Toast.makeText(JoinActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(TAG, "Join successful!");
                etJoinCode.setText("");
            }
        });
    }

    protected Challenge getJoinedChallenge(final String joinCode) {
        // Specify which class to query
        ParseQuery<Challenge> query = ParseQuery.getQuery(Challenge.class);
//        query.include(Challenge.KEY_JOIN_CODE);
        query.whereEqualTo(Challenge.KEY_JOIN_CODE, joinCode);
        final Challenge[] joinedChallenge = {new Challenge()};
        query.getFirstInBackground(new GetCallback<Challenge>() {
            @Override
            public void done(Challenge challenge, ParseException e) {
                if(e !=null){
                    Log.e(TAG, "Error in getting challenges: ", e);
                    return;
                }
                else{
                    joinedChallenge[0] = challenge;
                }
            }
        });
        return joinedChallenge[0];
    }
}