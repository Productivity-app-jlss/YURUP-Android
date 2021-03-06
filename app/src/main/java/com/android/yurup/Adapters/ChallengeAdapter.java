package com.android.yurup.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.yurup.DetailActivity;
import com.android.yurup.Models.Challenge;
import com.android.yurup.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    private Context context;
    private List<Challenge> challenges;
    public static final String TAG = "ChallengeAdapter";


    public ChallengeAdapter(Context context, List<Challenge> challenges) {
        this.context = context;
        this.challenges = challenges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_challenge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Challenge challenge = challenges.get(position);
        holder.bind(challenge);
    }


    @Override
    public int getItemCount() {
        return challenges.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvDescription;
        TextView tvStatus;
        TextView tvDueDate;
        View markStatus;
        LinearLayout challengeDet;
        FloatingActionButton fabStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.etTitle);
            tvDescription = itemView.findViewById(R.id.etDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
            markStatus = itemView.findViewById(R.id.markStatus);
            challengeDet = itemView.findViewById(R.id.challengeDet);
            fabStatus = itemView.findViewById(R.id.fab_status);

        }

        public void bind(final Challenge challenge) {
            tvTitle.setText(challenge.getTitle());
            tvDescription.setText(challenge.getDescription());
            tvStatus.setText(challenge.getHostStatus());
            tvDueDate.setText(challenge.getEnd());

            if(challenge.getIsActive() == true){
                fabStatus.setImageResource(R.drawable.ic_hourglass_top_24);
            }
            else{
                fabStatus.setImageResource(R.drawable.ic_done_16);
            }

//delete a challenge
            challengeDet.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    ParseQuery<Challenge> query = ParseQuery.getQuery(Challenge.class);

                    query.getInBackground(challenge.getObjectId(), new GetCallback<Challenge>() {
                        // showing the updated challenge on this screen
                        @Override
                        public void done(Challenge challenge, ParseException e) {
                            if (e == null) {
                                    challenge.deleteInBackground(new DeleteCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e != null)
                                                Log.e(TAG, "did not delete successfully", e);

                                        }
                                    });
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    return true;
                }
            });

            challengeDet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailChallengeIntent = new Intent(context, DetailActivity.class);
                    detailChallengeIntent.putExtra("challenge", Parcels.wrap(challenge));
                    context.startActivity(detailChallengeIntent);
                    //Toast.makeText(context, "detail info", Toast.LENGTH_SHORT).show();
                }
            });


            fabStatus.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

//                    Intent i = new Intent();
//                    challenge.getIsActive() = i.getBooleanExtra(challenge.getIsActive());
                    if(challenge.getIsActive() == false){
                        fabStatus.setImageResource(R.drawable.ic_hourglass_top_24);
                        challenge.setIsActive(true);
//                        challenge.saveInBackground(new SaveCallback() {
//                            @Override
//                            public void done(ParseException e) {
//                                if(e != null){
//                                    Log.e(TAG, "Update failed due to error: ", e);
//                                    Toast.makeText(context, "Save failed", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//                                Log.e(TAG, "Update successful!");
////                                challenge.setIsActive(false);
////                ivPhoto.setImageResource(0);
//                            }
//                        });
                        Toast.makeText(context, "Now Pending", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        fabStatus.setImageResource(R.drawable.ic_done_16);
                        challenge.setIsActive(false);
                        Toast.makeText(context, "Now Completed", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }



    }
}