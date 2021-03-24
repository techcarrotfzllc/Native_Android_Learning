package com.example.tashafinativeandroid.Modals;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookingSlots {
    @SerializedName("DoctorId")
    @Expose
    final String DoctorId;

    @SerializedName("AppointmentDate")
    @Expose
    final String AppointmentDate;

    @SerializedName("FacilityId")
    @Expose
    final String FacilityId;

    @SerializedName("AppointmentSlote")
    @Expose
    List<AppointmentSlots> appointmentSlots;


    public BookingSlots(String DoctorId, String AppointmentDate, String FacilityId) {
        this.DoctorId = DoctorId;
        this.AppointmentDate = AppointmentDate;
        this.FacilityId = FacilityId;
    }


    public List<AppointmentSlots> getAppointmentSlots() {
        return appointmentSlots;
    }

    public void setAppointmentSlots(List<AppointmentSlots> appointmentSlots) {
        this.appointmentSlots = appointmentSlots;
    }
}
