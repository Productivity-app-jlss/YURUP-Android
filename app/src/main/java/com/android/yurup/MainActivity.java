package com.android.yurup;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_main, fab_create, fab_join;
    boolean isRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        });

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