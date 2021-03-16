package com.example.tashafinativeandroid.remote;

import com.example.tashafinativeandroid.Modals.BookingAppointment;
import com.example.tashafinativeandroid.Modals.BookingSlots;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainInterface {
//    @Headers({
//            "Accept: application/json",
//            "Authorization: Basic dGFzaGFmaXRlc3RxYUBhbGZ1dHRhaW0tUFhDWE9ELkFFMVk4RTo2YmI1YjkxOC1jN2JmLTQ5YWQtOTU3Yi04ZjM0NjU5NzkxN2I="
//    })
    @POST("bookappointment")
    Call<BookingAppointment> bookAppointment(@Body BookingAppointment bookingAppointment);

//    @Headers({
//            "Accept: application/json",
//            "Authorization: Basic dGFzaGFmaXRlc3RxYUBhbGZ1dHRhaW0tUFhDWE9ELkFFMVk4RTo2YmI1YjkxOC1jN2JmLTQ5YWQtOTU3Yi04ZjM0NjU5NzkxN2I="
//    })
    @POST("doctorslots")
    Call<BookingSlots> getSlots(@Body BookingSlots slots);
}


