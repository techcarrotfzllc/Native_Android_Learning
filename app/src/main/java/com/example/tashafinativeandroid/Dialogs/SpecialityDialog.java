package com.example.tashafinativeandroid.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tashafinativeandroid.Adapters.FacilityAdapter;
import com.example.tashafinativeandroid.Adapters.SpecialityAdapter;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import java.util.ArrayList;

public class SpecialityDialog extends AppCompatDialogFragment {
    private SpecialityDialog.SpecialityDialogListener listener;
    public ListView listView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        listView = getView().findViewById(R.id.facility_listview);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.facility_dialog, null);
        listView = view.findViewById(R.id.facility_listview);
        Bundle bundle = getArguments();
        ArrayList<RetrofitModal> modal = (ArrayList<RetrofitModal>) bundle.getSerializable("Speciality Names");
        ArrayAdapter<RetrofitModal> itemsAdapter =
                new SpecialityAdapter(getContext(), modal);
//        for(RetrofitModal r : modal){
//            Log.v("fdsfdfdf fkdfk", r.getClinicHisId());
//        }

        listView.setAdapter(itemsAdapter);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String specialityId = modal.get(position).getSpecializationHisId();
                String specialityName = modal.get(position).getSpecializationName();
                listener.getSpecialityTexts(specialityId, specialityName);
//                closeDialog();
            }
        });

        builder.setView(view)
                .setTitle("Specialities")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SpecialityDialog.SpecialityDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement Dialog Listener");
        }
    }
    public interface SpecialityDialogListener {
        void getSpecialityTexts(String specialityId, String specialityName);
    }
}
