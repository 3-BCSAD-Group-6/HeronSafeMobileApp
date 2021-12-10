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

            hrecordId = view.findViewById(R.id.textviewHRecordID);
            hdate = view.findViewById(R.id.textviewHDate);
            hresult = view.findViewById(R.id.textviewHResult);
            mContainer = view.findViewById(R.id.history_container);

        }
    }


    @NonNull
    @Override
    public historyRecycleAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.history_list,parent,false);
        return new historyRecycleAdpter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyRecycleAdpter.MyViewHolder holder, int position) {

        final history history = historys.get(position);

        holder.hrecordId.setText(history.getRecord_number());
        holder.hdate.setText(history.getDate());
        holder.hresult.setText(history.getResult());


        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,HistoryActivity.class);

                intent.putExtra("record_number",history.getRecord_number());
                intent.putExtra("date",history.getDate());
                intent.putExtra("result",history.getResult());
                intent.putExtra("condition",history.getCondition());
                intent.putExtra("fever",history.getFever());
                intent.putExtra("cough",history.getCough());
                intent.putExtra("breathless",history.getBreathless());
                intent.putExtra("cold",history.getCold());
                intent.putExtra("sorethroat",history.getSorethroat());
                intent.putExtra("headache",history.getHeadache());
                intent.putExtra("no_symptoms",history.getNo_symptoms());
                intent.putExtra("exposure",history.getExposure());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }
}