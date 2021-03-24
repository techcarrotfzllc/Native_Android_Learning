package com.example.tashafinativeandroid.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tashafinativeandroid.Adapters.FacilityAdapter;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FacilityDialog extends AppCompatDialogFragment {
    private FacilityDialogListener listener;
    public ListView listView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        listView = getView().findViewById(R.id.facility_listview);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.facility_dialog, null);
        listView = view.findViewById(R.id.facility_listview);
        Bundle bundle = getArguments();
        ArrayList<RetrofitModal> modal = (ArrayList<RetrofitModal>) bundle.getSerializable("Facility Names");
        ArrayAdapter<RetrofitModal> itemsAdapter =
                new FacilityAdapter(getContext(), modal);
//        for(RetrofitModal r : modal){
//            Log.v("fdsfdfdf fkdfk", r.getClinicHisId());
//        }
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final Dialog dialog = dialog.create();
                String facilityId = modal.get(position).getClinicHisId();
                String facilityName = modal.get(position).getClinicName();
                listener.applyTexts(facilityId, facilityName);
//                closeDialog();
            }
        });

        builder.setView(view)
                .setTitle("Facilities")
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

//    public void closeDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (dialog != null) {
//                    dialog.dismiss();
//                }
//            }
//
//        });
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FacilityDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement Dialog Listener");
        }
    }

    public interface FacilityDialogListener {
        void applyTexts(String facilityId, String facilityName);
    }
}
