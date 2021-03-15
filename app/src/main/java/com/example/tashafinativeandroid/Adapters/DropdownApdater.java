package com.example.tashafinativeandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import java.util.ArrayList;

public class DropdownApdater extends ArrayAdapter<RetrofitModal> {
    public DropdownApdater(Context context, ArrayList<RetrofitModal> retrofitModals) {
        super(context, 0, retrofitModals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RetrofitModal retrofitModal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylist, parent, false);
        }

        TextView clinicHisId = (TextView) convertView.findViewById(R.id.clinicHisId);
        TextView clinicName = (TextView) convertView.findViewById(R.id.clinicName);
        TextView specializationHisId = (TextView) convertView.findViewById(R.id.specializationHisId);
        TextView specializationName = (TextView) convertView.findViewById(R.id.specializationName);
        TextView doctorHisId = (TextView) convertView.findViewById(R.id.doctorHisId);
        TextView doctorName = (TextView) convertView.findViewById(R.id.doctorName);

        clinicHisId.setText(retrofitModal.getClinicHisId());
        clinicName.setText(retrofitModal.getClinicName());
        specializationHisId.setText(retrofitModal.getSpecializationHisId());
        specializationName.setText(retrofitModal.getSpecializationName());
        doctorHisId.setText(retrofitModal.getDoctorHisId());
        doctorName.setText(retrofitModal.getDoctorName());

        return convertView;
    }
}
