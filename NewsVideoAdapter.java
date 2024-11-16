package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsVideoAdapter extends RecyclerView.Adapter<NewsVideoAdapter.ViewHolder> {
    public Context context;
    public ArrayList<YouTubeVideo> videos;

    public NewsVideoAdapter(Context context,ArrayList<YouTubeVideo> videos) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final YouTubeVideo video = videos.get(position);
        holder.descriptionTextView.setText(video.getTitle());
        video.getid();
        Picasso.get().load(video.getUrl()).into(holder.thumbnailImageView);
        holder.descriptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity2.class);
                i.putExtra("videoId", video.getid());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        public TextView descriptionTextView;
        public Button saveButton;
        public Button likeButton;
        public Button dislikeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            saveButton = itemView.findViewById(R.id.saveButton);
            likeButton = itemView.findViewById(R.id.likeButton);
            dislikeButton = itemView.findViewById(R.id.dislikeButton);
        }
    }
}
