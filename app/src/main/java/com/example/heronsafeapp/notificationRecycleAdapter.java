package com.example.heronsafeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class notificationRecycleAdapter extends RecyclerView.Adapter<notificationRecycleAdapter.MyViewHolder>{
    private Context mContext;
    private List<notification> notifications = new ArrayList<>();


    public notificationRecycleAdapter(Context context, List<notification> notifications){
        this.mContext = context;
        this.notifications = notifications;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nMessage, nCreated_by, nCreated_at;
        private AppCompatButton nDelete;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            nMessage = view.findViewById(R.id.textviewMessage);
            nCreated_by = view.findViewById(R.id.textviewSender);
            nCreated_at = view.findViewById(R.id.textviewDate);
            nDelete = view.findViewById(R.id.btnDeleteNotif);

            nDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.equals(nDelete)){
                removeAt(getBindingAdapterPosition());

            }
        }
    }

    public void removeAt(int layoutPosition) {
        notifications.remove(layoutPosition);
        notifyItemRemoved(layoutPosition);
        notifyItemRangeChanged(layoutPosition, notifications.size());
    }


    @NonNull
    @Override
    public notificationRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_list,parent,false);
        return new notificationRecycleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationRecycleAdapter.MyViewHolder holder, int position) {

        final notification notif = notifications.get(position);
        holder.nMessage.setText(notif.getMessage());
        holder.nCreated_by.setText(notif.getCreated_by());
        holder.nCreated_at.setText(notif.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
