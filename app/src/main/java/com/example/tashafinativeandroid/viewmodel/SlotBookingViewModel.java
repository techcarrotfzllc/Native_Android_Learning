package com.example.tashafinativeandroid.viewmodel;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tashafinativeandroid.Modals.BookingSlots;
import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.remote.HISAPIUtils;
import com.example.tashafinativeandroid.remote.MainInterface;
import com.example.tashafinativeandroid.remote.WPAPIUtils;
import com.example.tashafinativeandroid.remote.WpApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlotBookingViewModel extends ViewModel {

    private MutableLiveData<BookingSlots> bookingSlots;

    public SlotBookingViewModel() {
        bookingSlots = new MutableLiveData<>();
    }

    public MutableLiveData<BookingSlots> getSlotBooking() {
        return bookingSlots;
    }

    public void makeAPICall(ProgressDialog dialog, BookingSlots slots){

        dialog.setMessage("Please Wait...");

        dialog.setCancelable(false);

        dialog.show();

        MainInterface mainInterface = HISAPIUtils.getApiService();
        Call<BookingSlots> call = mainInterface.getSlots(slots);

        call.enqueue(new Callback<BookingSlots>() {
            @Override
            public void onResponse(Call<BookingSlots> call, Response<BookingSlots> response) {
//                retrofitModal.postValue(response.body());
                if (response.isSuccessful() && response.body() != null) {
                    BookingSlots rs = response.body();
                    Log.v("In succes", String.valueOf(rs));
                    bookingSlots.postValue(response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<BookingSlots> call, Throwable t) {
                bookingSlots.postValue(null);
                dialog.dismiss();
            }
        });

    }
}
