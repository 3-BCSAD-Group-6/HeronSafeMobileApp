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

public class historyRecycleAdpter extends RecyclerView.Adapter<historyRecycleAdpter.MyViewHolder> {

    private Context mContext;
    private List<history> historys = new ArrayList<>();


    public historyRecycleAdpter(Context context, List<history> historys){
        this.mContext = context;
        this.historys = historys;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hrecordId, hdate,hresult;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            hrecordId = view.findViewById(R.id.textViewRecordID);
            hdate = view.findViewById(R.id.textviewDate);
            hresult = view.findViewById(R.id.textviewResult);
            mContainer = view.findViewById(R.id.history_container);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.history_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final history history = historys.get(position);

        holder.hrecordId.setText(history.getRecord_id());
        holder.hdate.setText(history.getDate());
        holder.hresult.setText(history.getResult());


        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,HistoryActivity.class);

                intent.putExtra("record_number",history.getRecord_id());
                intent.putExtra("date",history.getDate());
                intent.putExtra("result",history.getResult());


                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }
}