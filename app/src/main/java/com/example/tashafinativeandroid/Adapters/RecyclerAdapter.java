package com.example.tashafinativeandroid.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {

    private ArrayList<RetrofitModal> dataArrayList;
    private Activity activity;
    private Context context;

    public RecyclerAdapter(ArrayList<RetrofitModal> dataArrayList, Activity activity, Context context) {
        this.dataArrayList = dataArrayList;
        this.activity = activity;
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RetrofitModal model = dataArrayList.get(position);

//       Glide.with(activity).load(model.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.clinicHisId.setText(model.getClinicHisId());
        holder.clinicName.setText(model.getClinicName());
        holder.doctorName.setText(model.getDoctorName());
        holder.doctorHisId.setText(model.getDoctorHisId());
        holder.specializationName.setText(model.getSpecializationName());
        holder.specializationHisId.setText(model.getSpecializationHisId());


//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("text", model.getName());
//                intent.putExtra("image", model.getImage());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView clinicHisId, clinicName, doctorName, doctorHisId, specializationHisId, specializationName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            clinicHisId = itemView.findViewById(R.id.clinicHisId);
            clinicName = itemView.findViewById(R.id.clinicName);
            doctorName = itemView.findViewById(R.id.doctorName);
            doctorHisId = itemView.findViewById(R.id.doctorHisId);
            specializationHisId = itemView.findViewById(R.id.specializationHisId);
            specializationName = itemView.findViewById(R.id.specializationName);

        }
    }
}
