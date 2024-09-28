package com.svecw.jokes;

import android.content.Context;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    Context context;
    List<Item> items;

    public BooksAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_item_design, parent, false);
        return new BooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        Glide.with(context).
                load(items.get(position).getVolumeInfo().getImageLinks().getThumbnail())
                .into(holder.iv);
        holder.tv.setText(items.get(position).getVolumeInfo().getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView2);
        }
    }
}
