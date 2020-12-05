package com.android.yurup.Adapters;

import android.content.Context;
import android.content.Intent;
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

import org.parceler.Parcels;

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    private Context context;
    private List<Challenge> challenges;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.etTitle);
            tvDescription = itemView.findViewById(R.id.etDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
            markStatus = itemView.findViewById(R.id.markStatus);
            challengeDet = itemView.findViewById(R.id.challengeDet);
        }

        public void bind(final Challenge challenge) {
            tvTitle.setText(challenge.getTitle());
            tvDescription.setText(challenge.getDescription());
            tvStatus.setText(challenge.getHostStatus());
            tvDueDate.setText(challenge.getEnd());

            challengeDet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailChallengeIntent = new Intent(context, DetailActivity.class);
                    detailChallengeIntent.putExtra("challenge", Parcels.wrap(challenge));
                    context.startActivity(detailChallengeIntent);
                    //Toast.makeText(context, "detail info", Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}