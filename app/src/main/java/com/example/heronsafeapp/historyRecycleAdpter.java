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

    private Context hContext;
    private List<history> historys = new ArrayList<>();


    public historyRecycleAdpter(Context context, List<history> historys){
        this.hContext = context;
        this.historys = historys;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView hrecordId, hdate,hresult;
        private LinearLayout hContainer;

        public MyViewHolder (View view){
            super(view);

            hrecordId = view.findViewById(R.id.textviewHRecordID);
            hdate = view.findViewById(R.id.textviewHDate);
            hresult = view.findViewById(R.id.textviewHResult);
            hContainer = view.findViewById(R.id.history_container);

        }
    }


    @NonNull
    @Override
    public historyRecycleAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(hContext).inflate(R.layout.history_list,parent,false);
        return new historyRecycleAdpter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyRecycleAdpter.MyViewHolder holder, int position) {

        final history histo = historys.get(position);

        holder.hrecordId.setText(histo.getRecord_number());
        holder.hdate.setText(histo.getDate());
        holder.hresult.setText(histo.getResult());


        holder.hContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(hContext,HistoryActivity.class);

                intent.putExtra("record_number",histo.getRecord_number());
                intent.putExtra("date",histo.getDate());
                intent.putExtra("result",histo.getResult());
                intent.putExtra("condition",histo.getCondition());
                intent.putExtra("fever",histo.getFever());
                intent.putExtra("cough",histo.getCough());
                intent.putExtra("breathless",histo.getBreathless());
                intent.putExtra("cold",histo.getCold());
                intent.putExtra("sorethroat",histo.getSorethroat());
                intent.putExtra("headache",histo.getHeadache());
                intent.putExtra("no_symptoms",histo.getNo_symptoms());
                intent.putExtra("exposure",histo.getExposure());

                hContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }
}