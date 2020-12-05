package com.android.yurup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.yurup.Models.Challenge;
import com.android.yurup.challengeActivities.CreateActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class EditActivity extends AppCompatActivity {

    public static final String TAG = "EditActivity";

    EditText etTitle;
    EditText etDescription;
    EditText etStartDate;
    EditText etEndDate;
    Button btnUpdate;

    String title, description, startDate, endDate, objectID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);

        Intent i = getIntent();

        title = i.getStringExtra(DetailActivity.KEY_TITLE);
        description = i.getStringExtra(DetailActivity.KEY_DESCRIPTION);
        startDate = i.getStringExtra(DetailActivity.KEY_START);
        endDate = i.getStringExtra(DetailActivity.KEY_END);
        objectID = i.getStringExtra(DetailActivity.KEY_ID);


        etTitle.setText(i.getStringExtra(DetailActivity.KEY_TITLE));
        etDescription.setText(i.getStringExtra(DetailActivity.KEY_DESCRIPTION));
        etStartDate.setText(i.getStringExtra(DetailActivity.KEY_START));
        etEndDate.setText(i.getStringExtra(DetailActivity.KEY_END));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                ParseUser currentUser = ParseUser.getCurrentUser();
                String endDate = etEndDate.getText().toString();
                String startDate = etStartDate.getText().toString();


                try {
                    // updating the Api
                    saveChallenge(title, description, currentUser, endDate, startDate, objectID);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                //create an intent to contain the result
                Intent intent = new Intent();

                // pass the result
               intent.putExtra(DetailActivity.KEY_ITEM_TEXT, etTitle.getText().toString());

                //set the result of the intent
                setResult(RESULT_OK, intent);

                //finish activity, close the screen and go back
                finish();
            }
        });


    }

    private void saveChallenge(String title, String description, ParseUser currentUser, String endDate, String startDate, String objectID) throws java.text.ParseException {

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
        challenge.setObjectId(objectID);
        //challenge.setJoinCode(randomString(6, new SecureRandom(), alphanum));
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
                    Toast.makeText(EditActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
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