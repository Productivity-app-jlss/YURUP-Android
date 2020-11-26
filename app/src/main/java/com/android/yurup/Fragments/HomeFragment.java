package com.android.yurup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yurup.Animation;
import com.android.yurup.Challenge;
import com.android.yurup.ChallengeAdapter;
import com.android.yurup.MainActivity;
import com.android.yurup.R;
import com.android.yurup.createActivity;
import com.android.yurup.joinActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class HomeFragment extends Fragment {

    private RecyclerView rvChallenges;
    public static final String TAG = "HomeFragment";
    protected ChallengeAdapter adapter;
    protected List<Challenge> allChallenges;

    private FloatingActionButton fab_main, fab_create, fab_join;
    boolean isRotate = false;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvChallenges = view.findViewById(R.id.rvChallenges);
        allChallenges = new ArrayList<>();
        adapter = new ChallengeAdapter(getContext(), allChallenges);
        // Steps for recycler view
        // Create Layout for one row in the list
        // create the adapter
        // create the data source
        // set adapter on recycler view
        rvChallenges.setAdapter(adapter);
        // set layout manager on recycler view
        rvChallenges.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab_main = view.findViewById(R.id.fab_main);
        final FloatingActionButton fab_create = view.findViewById(R.id.fab_create);
        final FloatingActionButton fab_join = view.findViewById(R.id.fab_join);

        Animation.init(fab_create);
        Animation.init(fab_join);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this, "Add challenge", Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(MainActivity.this, "Create challenge", Toast.LENGTH_SHORT).show();
                Intent create_activity = new Intent(getApplicationContext(), createActivity.class);
                startActivity(create_activity);
            }
        });

        fab_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Join challenge", Toast.LENGTH_SHORT).show();
                Intent join_activity = new Intent(getApplicationContext(), joinActivity.class);
                startActivity(join_activity);
            }
        });

        queryChallenges();
    }


    protected void queryChallenges() {
        // Specify which class to query
        ParseQuery<Challenge> query = ParseQuery.getQuery(Challenge.class);
        query.include(Challenge.KEY_TITLE);
        query.setLimit(20);
        query.addAscendingOrder(Challenge.KEY_END_DATE);
        query.findInBackground(new FindCallback<Challenge>() {
            @Override
            public void done(List<Challenge> challenges, ParseException e) {
                if(e !=null){
                    Log.e(TAG, "Error in getting challenges: ", e);
                    return;
                }
                for(Challenge challenge: challenges){
                    Log.i(TAG, "Challenges: " + challenge.getDescription() + ", title: " + challenge.getTitle());
                }

                allChallenges.addAll(challenges);
                adapter.notifyDataSetChanged();
            }
        });
    }
}