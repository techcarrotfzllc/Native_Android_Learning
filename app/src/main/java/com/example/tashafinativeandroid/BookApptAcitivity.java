package com.example.tashafinativeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tashafinativeandroid.Modals.BookingAppointment;
import com.example.tashafinativeandroid.remote.HISAPIUtils;
import com.example.tashafinativeandroid.remote.MainInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookApptAcitivity extends AppCompatActivity {

    Button button;
    EditText name, email, phonenumber;
    String getName, getEmail, getPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appt_acitivity);

        button = findViewById(R.id.confirm);
        name = findViewById(R.id.edit_text_name);
        email = findViewById(R.id.edit_text_email);
        phonenumber = findViewById(R.id.edit_text_phone_number);

        getName = name.getText().toString();
        getEmail = email.getText().toString();
        getPhoneNumber = phonenumber.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getName.isEmpty() && !getEmail.isEmpty() && !getPhoneNumber.isEmpty()){
                    setBookAppointment();
                }
                else{
                    Toast.makeText(BookApptAcitivity.this, "Please check all the required fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setBookAppointment() {

        BookingAppointment bookingAppointment = new BookingAppointment(30, "App", "2021-02-04", "11", 1, "REGULAR", "08:30", "4750", "PAA", "1", "Shabeer.mohamed-external@alfuttaim.com", "Shabeer Mohamed TEST", "0544632511", "200002840", "12235");

        MainInterface mainInterface = HISAPIUtils.getApiService();

        Call<BookingAppointment> call = mainInterface.bookAppointment(bookingAppointment);

        ProgressDialog dialog = new ProgressDialog(BookApptAcitivity.this);
        dialog.setMessage("Please Wait...");

        dialog.setCancelable(false);

        dialog.show();

        call.enqueue(new Callback<BookingAppointment>() {
            @Override
            public void onResponse(Call<BookingAppointment> call, Response<BookingAppointment> response) {

                if (response.isSuccessful() && response.body() != null) {
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