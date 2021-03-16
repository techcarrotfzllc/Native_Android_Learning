package com.example.tashafinativeandroid.viewmodel;

import android.app.ProgressDialog;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tashafinativeandroid.Modals.RetrofitModal;
import com.example.tashafinativeandroid.remote.WPAPIUtils;
import com.example.tashafinativeandroid.remote.WpApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MappingViewModel extends ViewModel {

    private MutableLiveData<List<RetrofitModal>> retrofitModal;

    public MappingViewModel() {
        retrofitModal = new MutableLiveData<>();
    }

    public MutableLiveData<List<RetrofitModal>> getMappingList() {
        return retrofitModal;
    }

    public void makeAPICall(ProgressDialog dialog){

        dialog.setMessage("Please Wait...");

        dialog.setCancelable(false);

        dialog.show();

        WpApiInterface wpApiInterface = WPAPIUtils.getApiService();
        Call<List<RetrofitModal>> call = wpApiInterface.getMappingData();

        call.enqueue(new Callback<List<RetrofitModal>>() {
            @Override
            public void onResponse(Call<List<RetrofitModal>> call, Response<List<RetrofitModal>> response) {
//                retrofitModal.postValue(response.body());
                if (response.isSuccessful() && response.body() != null) {
                    List<RetrofitModal> rs = response.body();
                    Log.v("In succes", String.valueOf(rs));
                    retrofitModal.postValue(response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitModal>> call, Throwable t) {
                retrofitModal.postValue(null);
                dialog.dismiss();
            }
        });

    }
}
