package com.example.tashafinativeandroid.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tashafinativeandroid.Adapters.DoctorAdapter;
import com.example.tashafinativeandroid.Adapters.SpecialityAdapter;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class DoctorsDialog extends AppCompatDialogFragment {
    private DoctorDialogListener listener;
    public ListView listView;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.facility_dialog, null);
        listView = view.findViewById(R.id.facility_listview);
        Bundle bundle = getArguments();
        ArrayList<RetrofitModal> modal = (ArrayList<RetrofitModal>) bundle.getSerializable("Doctor Names");
//        for(RetrofitModal r : modal){
//            Log.v("fdsfdfdf fkdfk", r.getClinicHisId());
//        }
        ArrayAdapter<RetrofitModal> itemsAdapter =
                new DoctorAdapter(getContext(), modal);
//        for(RetrofitModal r : modal){
//            Log.v("fdsfdfdf fkdfk", r.getClinicHisId());
//        }

        listView.setAdapter(itemsAdapter);
        builder.setView(view)
                .setTitle("Doctors")
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
            listener = (DoctorDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement Dialog Listener");
        }
    }
    public interface DoctorDialogListener {
        void applyTexts(String username, String password);
    }
}

