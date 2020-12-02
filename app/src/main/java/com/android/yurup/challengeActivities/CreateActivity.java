package com.android.yurup.challengeActivities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
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
import com.android.yurup.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class CreateActivity extends AppCompatActivity {

    public static final String TAG = "CreateActivity";
    EditText etTitle;
    EditText etDescription;
    EditText etStartDate;
    EditText etEndDate;

    BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Toast.makeText(CreateActivity.this, "profile", Toast.LENGTH_SHORT).show();
                        fragment = new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(CreateActivity.this, "home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        Button btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                ParseUser currentUser = ParseUser.getCurrentUser();
                String endDate = etEndDate.getText().toString();
                String startDate = etStartDate.getText().toString();

                try {
                    saveChallenge(title, description, currentUser, endDate, startDate);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                Toast.makeText(CreateActivity.this, "Created Challenge", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveChallenge(String title, String description, ParseUser currentUser, String endDate, String startDate) throws java.text.ParseException {

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "0123456789";
        String alphanum = upper + lower + digits;

        Challenge challenge = new Challenge();
        challenge.setTitle(title);
        challenge.setDescription(description);
        challenge.setStart(startDate);
        challenge.setEnd(endDate);
//        challenge.setImage(new ParseFile(photoFile));
        challenge.setCreator(currentUser);
        challenge.setHostStatus("PENDING");
        challenge.setJoinCode(randomString(6, new SecureRandom(), alphanum));
        if(new SimpleDateFormat("MM/dd/yyyy").parse(endDate).before(new Date())){
            challenge.setIsActive(true);
        }
        else{
            challenge.setIsActive(false);
        }
        challenge.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Save failed due to error: ", e);
                    Toast.makeText(CreateActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e(TAG, "Save successful!");
                etTitle.setText("");
                etDescription.setText("");
                etStartDate.setText("");
                etEndDate.setText("");
//                ivPhoto.setImageResource(0);
            }
        });
    }


    public String randomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        Random rand = Objects.requireNonNull(random);
        char[] sym = symbols.toCharArray();
        char[] buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = sym[random.nextInt(sym.length)];
        return new String(buf);
    }
}