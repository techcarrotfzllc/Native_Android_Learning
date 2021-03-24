package com.example.tashafinativeandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tashafinativeandroid.Adapters.RecyclerAdapter;
import com.example.tashafinativeandroid.Dialogs.DoctorsDialog;
import com.example.tashafinativeandroid.Dialogs.FacilityDialog;
import com.example.tashafinativeandroid.Dialogs.SpecialityDialog;
import com.example.tashafinativeandroid.Modals.BookingSlots;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.remote.RetrofitClient1;
import com.example.tashafinativeandroid.viewmodel.MappingViewModel;
import com.example.tashafinativeandroid.viewmodel.SlotBookingViewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements FacilityDialog.FacilityDialogListener, SpecialityDialog.SpecialityDialogListener, DoctorsDialog.DoctorDialogListener {

    ArrayList<RetrofitModal> dataArrayList = new ArrayList<>();
    Set<RetrofitModal> set = new HashSet<RetrofitModal>();
    RecyclerAdapter adapter;
    ArrayList<RetrofitModal> clinicNames = new ArrayList<RetrofitModal>();
    ArrayList<RetrofitModal> doctorNames = new ArrayList<RetrofitModal>();
    ArrayList<RetrofitModal> specialitiesNames = new ArrayList<RetrofitModal>();
    ArrayList<RetrofitModal> clinicNamesWithoutDuplicates = new ArrayList<>();
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    Button button, dateButton, facilityButton, specialityButton, doctorButton;
    TextView slotText;
    private MappingViewModel viewModel;
    private SlotBookingViewModel slotsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        spinnerOne = findViewById(R.id.static_spinner);
//        spinnerTwo = findViewById(R.id.static_spinner1);
//        spinnerThree = findViewById(R.id.static_spinner2);
        button = findViewById(R.id.next);
        dateButton = findViewById(R.id.date_button);
        facilityButton = findViewById(R.id.facilityButton);
        specialityButton = findViewById(R.id.specialityButton);
        doctorButton = findViewById(R.id.doctorButton);
        slotText = (TextView) findViewById(R.id.slotText);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

//        clinicNames.add(0, );
//        specialitiesNames.add(0, "Select Speciality");
//        doctorNames.add(0, "Select Doctors");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookApptAcitivity.class);
                startActivity(intent);
            }
        });

        facilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clinicNames.isEmpty()){
                    openFacilityDialog(clinicNames);
                }
                else{
                    //
                }
            }
        });

        specialityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!specialitiesNames.isEmpty()){
                    openSpecialityDialog(specialitiesNames);
                }
                else{
                    //
                }
            }
        });

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!doctorNames.isEmpty()){
                    openDoctorDialog(doctorNames);
                }
                else{
                    //
                }
            }
        });

        getData();
    }

    public void openFacilityDialog(ArrayList<RetrofitModal> clinicNames){
//        clinicNames.clear();
//        clinicNames.addAll(set);

        clinicNamesWithoutDuplicates = (ArrayList<RetrofitModal>) clinicNames.stream().distinct().collect(Collectors.toList());
        FacilityDialog facilityDialog = new FacilityDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Facility Names",clinicNamesWithoutDuplicates);
        facilityDialog.setArguments(bundle);
        facilityDialog.show(getSupportFragmentManager(), "Facility dialog");
    }

    public void openSpecialityDialog(ArrayList<RetrofitModal> clinicNames){
        SpecialityDialog specialityDialog = new SpecialityDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Speciality Names", specialitiesNames);
        specialityDialog.setArguments(bundle);
        specialityDialog.show(getSupportFragmentManager(), "Speciality dialog");
    }

    public void openDoctorDialog(ArrayList<RetrofitModal> clinicNames){
        DoctorsDialog doctorsDialog = new DoctorsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Doctor Names",doctorNames);
        doctorsDialog.setArguments(bundle);
        doctorsDialog.show(getSupportFragmentManager(), "Doctor dialog");
    }


    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    getTimeSlots();
                }
            };


    private void getData() {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        viewModel = ViewModelProviders.of(this).get(MappingViewModel.class);
        viewModel.getMappingList().observe(this, new Observer<List<RetrofitModal>>() {
            @Override
            public void onChanged(List<RetrofitModal> movieModels) {
                if (movieModels != null) {
                    parseArray(movieModels);
                }
            }
        });

        viewModel.makeAPICall(dialog);
    }

    private void getTimeSlots() {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        BookingSlots slots = new BookingSlots("5327", "2021-03-15", "11");

        slotsViewModel = ViewModelProviders.of(this).get(SlotBookingViewModel.class);
        slotsViewModel.getSlotBooking().observe(this, new Observer<BookingSlots>() {
            @Override
            public void onChanged(BookingSlots bookingSlots) {
                if (bookingSlots != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(bookingSlots));
                        JSONArray jsonArray = jsonObject.getJSONArray("AppointmentSlote");
                        Log.v("Check Response", String.valueOf(jsonArray));
                        for (int i = 0; i < jsonArray.length() ; i ++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String content = "" + "\n";
                            content += jsonObject1.getString("TimeSlot") + "\n";
                            Log.v("fdf content", content);
                            slotText.append(content);
                        }
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
            }
        });

        slotsViewModel.makeAPICall(dialog, slots);

    }

    private void parseArray(List<RetrofitModal> rs) {

        dataArrayList.clear();

        for (RetrofitModal r : rs) {
            Log.v("fdfdgfg", String.valueOf(r.getClinicHisId()));
            clinicNames.add(r);
            specialitiesNames.add(r);
            doctorNames.add(r);
        }

//        Set<String> set = new HashSet<>(clinicNames);
//        clinicNames.clear();
//        clinicNames.addAll(set);

//        ArrayAdapter<RetrofitModal> adapterOne = new ArrayAdapter<RetrofitModal>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, clinicNames);
//        ArrayAdapter<RetrofitModal> adapterTwo = new ArrayAdapter<RetrofitModal>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, specialitiesNames);
//        ArrayAdapter<RetrofitModal> adapterThree = new ArrayAdapter<RetrofitModal>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, doctorNames);
//        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        adapterThree.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinnerOne.setAdapter(adapterOne);
//        spinnerTwo.setAdapter(adapterTwo);
//        spinnerThree.setAdapter(adapterThree);
//        spinnerOne.setSelection(0);


//        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i > 0) {
//                    Toast.makeText(MainActivity.this, "" + clinicNames.get(i), Toast.LENGTH_SHORT).show();
////                    Log.v("doctornames", doctorNames.stream().filter(d -> ));
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i > 0) {
//                    Toast.makeText(MainActivity.this, "" + specialitiesNames.get(i), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i > 0) {
//                    Toast.makeText(MainActivity.this, "" + doctorNames.get(i), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    @Override
    public void applyTexts(String facilityId, String facilityName) {
        facilityButton.setText(facilityName);
    }

    @Override
    public void getSpecialityTexts(String specialityId, String specialityName) {
        specialityButton.setText(specialityName);
    }
}