package com.example.tashafinativeandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import java.util.ArrayList;

public class SpecialityAdapter extends ArrayAdapter<RetrofitModal> {
    private static class ViewHolder {
        TextView name;
    }

    public SpecialityAdapter(@NonNull Context context, ArrayList<RetrofitModal> retrofitModal) {
        super(context,0, retrofitModal);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RetrofitModal retrofitModal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        SpecialityAdapter.ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new SpecialityAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.my_dialog_list, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.facility_text);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (SpecialityAdapter.ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.name.setText(retrofitModal.getSpecializationName());
        // Return the completed view to render on screen
        return convertView;
    }
}
