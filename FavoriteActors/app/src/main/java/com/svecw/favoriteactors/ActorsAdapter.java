package com.svecw.favoriteactors;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActorsAdapter extends
        RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder> {

    Context context;
    List<Actors> actors;

    public ActorsAdapter(Context context, List<Actors> actors) {
        this.context = context;
        this.actors = actors;
    }

    @NonNull
    @Override
    public ActorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_item_design,parent,false);
        return new ActorsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsViewHolder holder, int position) {
        holder.actor_poster.setImageResource(actors.get(position).image);
        holder.actor_name.setText(actors.get(position).name);
        holder.yob.setText(String.valueOf(actors.get(position).yob));
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ActorsViewHolder extends RecyclerView.ViewHolder {
        ImageView actor_poster;
        TextView actor_name, yob;
        public ActorsViewHolder(@NonNull View itemView) {
            super(itemView);
            actor_poster = itemView.findViewById(R.id.actor_image);
            actor_name = itemView.findViewById(R.id.actor_name);
            yob = itemView.findViewById(R.id.actor_yob);
        }
    }
}
