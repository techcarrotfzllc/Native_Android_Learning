package com.example.tashafinativeandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tashafinativeandroid.Adapters.RecyclerAdapter;
import com.example.tashafinativeandroid.Modals.BookingSlots;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.remote.HISAPIUtils;
import com.example.tashafinativeandroid.remote.MainInterface;
import com.example.tashafinativeandroid.remote.WPAPIUtils;
import com.example.tashafinativeandroid.remote.WpApiInterface;
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
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Spinner spinnerOne, spinnerTwo, spinnerThree;
    ArrayList<RetrofitModal> dataArrayList = new ArrayList<>();
    RecyclerAdapter adapter;
    ArrayList<String> clinicNames = new ArrayList<String>();
    ArrayList<String> doctorNames = new ArrayList<String>();
    ArrayList<String> specialitiesNames = new ArrayList<String>();
    Set<String> set = new HashSet<String>();
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    Button button, dateButton;
    TextView slotText;
    private MappingViewModel viewModel;
    private SlotBookingViewModel slotsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerOne = findViewById(R.id.static_spinner);
        spinnerTwo = findViewById(R.id.static_spinner1);
        spinnerThree = findViewById(R.id.static_spinner2);
        button = findViewById(R.id.next);
        dateButton = findViewById(R.id.date_button);
        slotText = (TextView) findViewById(R.id.slotText);
//        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month + 1, day);

        clinicNames.add(0, "Select Location");
        specialitiesNames.add(0, "Select Speciality");
        doctorNames.add(0, "Select Doctors");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookApptAcitivity.class);
                startActivity(intent);
            }
        });

        getData();
    }

    @SuppressWarnings("deprecation")
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
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
//                    showDate(arg1, arg2 + 1, arg3);
                    getTimeSlots();
                }
            };

//    private void showDate(int year, int month, int day) {
//        dateView.setText(new StringBuilder().append(day).append("/")
//                .append(month).append("/").append(year));
//    }

    private void getData() {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
//        dialog.setMessage("Please Wait...");
//
//        dialog.setCancelable(false);
//
//        dialog.show();
        viewModel = ViewModelProviders.of(this).get(MappingViewModel.class);
        viewModel.getMappingList().observe(this, new Observer<List<RetrofitModal>>() {
            @Override
            public void onChanged(List<RetrofitModal> movieModels) {
                if (movieModels != null) {
                    parseArray(movieModels);
//                } else {
//                    noresult.setVisibility(View.VISIBLE);
//                }
                }
            }
        });

        viewModel.makeAPICall(dialog);

//        WpApiInterface wpApiInterface = WPAPIUtils.getApiService();
//
//        Call<List<RetrofitModal>> Call = wpApiInterface.getMappingData();
//
//        Call.enqueue(new Callback<List<RetrofitModal>>() {
//            @Override
//            public void onResponse(Call<List<RetrofitModal>> call, Response<List<RetrofitModal>> response) {
//                Log.v("checkfkdfl;dkf", response.toString());
//                if (response.isSuccessful() && response.body() != null) {
//                    List<RetrofitModal> rs = response.body();
//                    Log.v("In succes", String.valueOf(rs));
//                    parseArray(rs);
//                    dialog.dismiss();
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<List<RetrofitModal>> call, Throwable t) {
//                Log.v("In failure", "Fdffd");
//                dialog.dismiss();
//            }
//        });
    }

    private void getTimeSlots() {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        BookingSlots slots = new BookingSlots("5327", "2021-03-15", "11");

        slotsViewModel = ViewModelProviders.of(this).get(SlotBookingViewModel.class);
        slotsViewModel.getSlotBooking().observe(this, new Observer<BookingSlots>() {
            @Override
            public void onChanged(BookingSlots bookingSlots) {
                if (bookingSlots != null) {

//                } else {
//                    noresult.setVisibility(View.VISIBLE);
//                }
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

//        MainInterface mainInterface = HISAPIUtils.getApiService();
//
//        Call<BookingSlots> call = mainInterface.getSlots(slots);
//
//        ProgressDialog dialog = new ProgressDialog(MainActivity.this);
//        dialog.setMessage("Please Wait...");
//
//        dialog.setCancelable(false);
//
//        dialog.show();
//
//        call.enqueue(new Callback<BookingSlots>() {
//            @Override
//            public void onResponse(Call<BookingSlots> call, Response<BookingSlots> response) {
//                Log.v("SLOTS RES", String.valueOf(response));
//                if (!response.isSuccessful()) {
//                    dialog.dismiss();
//                    Toast.makeText(MainActivity.this, "Something went wrong. Please try again." + response, Toast.LENGTH_SHORT).show();
//                }
//
//                try {
//                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
//                    JSONArray jsonArray = jsonObject.getJSONArray("AppointmentSlote");
//                    Log.v("Check Response", String.valueOf(jsonArray));
//                    for (int i = 0; i < jsonArray.length() ; i ++) {
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                        String content = "" + "\n";
//                        content += jsonObject1.getString("TimeSlot") + "\n";
//                        Log.v("fdf content", content);
//                        slotText.append(content);
//                    }
//                } catch (JSONException jsonException) {
//                    jsonException.printStackTrace();
//                }
//                dialog.dismiss();
//
//            }
//
//            @Override
//            public void onFailure(Call<BookingSlots> call, Throwable t) {
//                Log.v("Slots Error", t.toString());
//                dialog.dismiss();
//            }
//        });

    }

    private void parseArray(List<RetrofitModal> rs) {

        dataArrayList.clear();

        for (RetrofitModal r : rs) {
            Log.v("fdfdgfg", String.valueOf(r.getClinicHisId()));
            clinicNames.add(r.getClinicName());
            specialitiesNames.add(r.getSpecializationName());
            doctorNames.add(r.getDoctorName());
        }

        Set<String> set = new HashSet<>(clinicNames);
        clinicNames.clear();
        clinicNames.addAll(set);

        ArrayAdapter<String> adapterOne = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, clinicNames);
        ArrayAdapter<String> adapterTwo = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, specialitiesNames);
        ArrayAdapter<String> adapterThree = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, doctorNames);
        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterThree.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerOne.setAdapter(adapterOne);
        spinnerTwo.setAdapter(adapterTwo);
        spinnerThree.setAdapter(adapterThree);
        spinnerOne.setSelection(0);


        spinnerOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) {
                    Toast.makeText(MainActivity.this, "" + clinicNames.get(i), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) {
                    Toast.makeText(MainActivity.this, "" + specialitiesNames.get(i), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) {
                    Toast.makeText(MainActivity.this, "" + doctorNames.get(i), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}