package com.example.tashafinativeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tashafinativeandroid.remote.HISAPIUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookApptAcitivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appt_acitivity);

        button = findViewById(R.id.confirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBookAppointment();
            }
        });

    }

    private void setBookAppointment() {

//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://eu.test.connect.boomi.com/ws/rest/healthcare/healthhub/v1/")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();

        BookingAppointment bookingAppointment = new BookingAppointment(30, "App", "2021-02-04", "11", 1, "REGULAR", "08:30", "4750", "PAA", "1", "Shabeer.mohamed-external@alfuttaim.com", "Shabeer Mohamed TEST", "0544632511", "200002840", "12235");

//        MainInterface mainInterface = retrofit.create(MainInterface.class);

        MainInterface mainInterface = HISAPIUtils.getApiService();

        Call<BookingAppointment> call = mainInterface.bookAppointment(bookingAppointment);

        ProgressDialog dialog = new ProgressDialog(BookApptAcitivity.this);
        dialog.setMessage("Please Wait...");

        dialog.setCancelable(false);

        dialog.show();

        call.enqueue(new Callback<BookingAppointment>() {
            @Override
            public void onResponse(Call<BookingAppointment> call, Response<BookingAppointment> response) {

//                if (!response.isSuccessful()) {
//                    dialog.dismiss();
//                    Toast.makeText(getApplicationContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
//                }
//
//                try {
//                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
//                    JSONArray jsonArray = jsonObject.getJSONArray("AppointmentSlote");
//                    Log.v("Check Response", String.valueOf(jsonArray));
//                    dialog.dismiss();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    dialog.dismiss();
//                }

                if (response.isSuccessful() && response.body() != null) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
//                        Log.v("Book Appointmet", String.valueOf(jsonObject));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    dialog.dismiss();
                    Toast.makeText(BookApptAcitivity.this, "Your Appointment is booked successfully with Appointmet Id : " + response.body().getAppointmentId() + " and appointment status" + response.body().getStatusMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();
                    Toast.makeText(BookApptAcitivity.this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookingAppointment> call, Throwable t) {
                Log.v("Apptmt Error", t.toString());
                dialog.dismiss();
            }
        });


    }
}