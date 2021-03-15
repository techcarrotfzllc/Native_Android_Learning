package com.example.tashafinativeandroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingAppointment {

    final Integer Interval;
    final String Channel;
    final String AppointmentDate;
    final String FacilityId;
    final Integer VisitTypeId;
    final String AppointmentType;
    final String FromTime;
    final String DoctorId;
    final String AppointmentSource;
    final String HospitalLocationId;
    final String PatientEmail;
    final String PatientName;
    final String PatientMobileNumber;
    final String RegistrationNo;
    final String RegistrationId;
    @SerializedName("AppointmentId")
    @Expose()
    String AppointmentId;
    @SerializedName("StatusMessage")
    @Expose()
    String StatusMessage;


    public BookingAppointment(Integer interval, String channel, String appointmentDate, String facilityId, Integer visitTypeId, String appointmentType, String fromTime, String doctorId, String appointmentSource, String hospitalLocationId, String patientEmail, String patientName, String patientMobileNumber, String registrationNo, String registrationId) {
        Interval = interval;
        Channel = channel;
        AppointmentDate = appointmentDate;
        FacilityId = facilityId;
        VisitTypeId = visitTypeId;
        AppointmentType = appointmentType;
        FromTime = fromTime;
        DoctorId = doctorId;
        AppointmentSource = appointmentSource;
        HospitalLocationId = hospitalLocationId;
        PatientEmail = patientEmail;
        PatientName = patientName;
        PatientMobileNumber = patientMobileNumber;
        RegistrationNo = registrationNo;
        RegistrationId = registrationId;
    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getStatusMessage() {
        return StatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        StatusMessage = statusMessage;
    }
}
