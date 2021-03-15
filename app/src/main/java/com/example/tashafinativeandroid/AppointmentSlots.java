package com.example.tashafinativeandroid;

import com.google.gson.annotations.SerializedName;

public class AppointmentSlots {
    @SerializedName("TimeSlot")
    final String TimeSlot;
    @SerializedName("IsAvailable")
    final boolean IsAvailable;


    public AppointmentSlots(String timeSlot, boolean isAvailable) {
        TimeSlot = timeSlot;
        IsAvailable = isAvailable;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }
}
