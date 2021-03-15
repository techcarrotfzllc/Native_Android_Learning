package com.example.tashafinativeandroid;

import com.example.tashafinativeandroid.Modals.RetrofitModal;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WpApiInterface {
//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/json",
//            "Authorization: Basic dGFzaGFmaXRlc3RxYUBhbGZ1dHRhaW0tUFhDWE9ELkFFMVk4RTo2YmI1YjkxOC1jN2JmLTQ5YWQtOTU3Yi04ZjM0NjU5NzkxN2I="
//    })
    @POST("mapping-list")
    Call<List<RetrofitModal>> getMappingData();

//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/json",
//            "Authorization: Basic dGFzaGFmaXRlc3RxYUBhbGZ1dHRhaW0tUFhDWE9ELkFFMVk4RTo2YmI1YjkxOC1jN2JmLTQ5YWQtOTU3Yi04ZjM0NjU5NzkxN2I="
//    })
    @POST("appointment-booking")
    Call<ResponseBody> insertBooking(@Body RequestBody body);
}
