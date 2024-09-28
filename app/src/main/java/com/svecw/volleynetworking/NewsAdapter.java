package com.svecw.volleynetworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsVH> {
    Context context;
    List<Article> articles;

    public NewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.one_item_design, parent, false);
        return new NewsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsVH holder, int position) {
        Glide.with(context).load(articles.get(position).getUrlToImage()).into(holder.iv);
        holder.tv.setText(articles.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class NewsVH extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public NewsVH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tv = itemView.findViewById(R.id.textView);
        }
    }
}
