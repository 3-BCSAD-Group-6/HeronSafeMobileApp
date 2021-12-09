package com.example.heronsafeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class announcementRecycleAdapter extends RecyclerView.Adapter<announcementRecycleAdapter.MyViewHolder> {
    private Context mContext;
    private List<announcement> announcements = new ArrayList<>();


    public announcementRecycleAdapter(Context context, List<announcement> announcements){
        this.mContext = context;
        this.announcements = announcements;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView aSubject,aMessage, aCreated_by, aCreated_at;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            aSubject = view.findViewById(R.id.textviewSubject);
            aMessage = view.findViewById(R.id.textviewMessage);
            aCreated_by = view.findViewById(R.id.textviewPostedBy);
            aCreated_at = view.findViewById(R.id.textviewDate);
            mContainer = view.findViewById(R.id.announcement_container);

        }
    }


    @NonNull
    @Override
    public announcementRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.announcement_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull announcementRecycleAdapter.MyViewHolder holder, int position) {

        final announcement announcement = announcements.get(position);

        holder.aSubject.setText(announcement.getSubject());
        holder.aMessage.setText(announcement.getMessage());
        holder.aCreated_by.setText(announcement.getCreated_by());
        holder.aCreated_at.setText(announcement.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }
}
