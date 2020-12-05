package com.android.yurup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.yurup.Models.Challenge;

import org.parceler.Parcels;

public class EditActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etDescription;
    EditText etStartDate;
    EditText etEndDate;
    Button btnUpdate;

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

        etTitle.setText(i.getStringExtra(DetailActivity.KEY_TITLE));
        etDescription.setText(i.getStringExtra(DetailActivity.KEY_DESCRIPTION));
        etStartDate.setText(i.getStringExtra(DetailActivity.KEY_START));
        etEndDate.setText(i.getStringExtra(DetailActivity.KEY_END));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}