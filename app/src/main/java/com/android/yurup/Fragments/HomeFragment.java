package com.android.yurup.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yurup.Challenge;
import com.android.yurup.ChallengeAdapter;
import com.android.yurup.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvChallenges;
    public static final String TAG = "HomeFragment";
    protected ChallengeAdapter adapter;
    protected List<Challenge> allChallenges;

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