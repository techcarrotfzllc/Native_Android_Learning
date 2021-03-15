package com.example.tashafinativeandroid.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitModal {
    @SerializedName("clinic_his_id")
    @Expose
    String clinicHisId;

    @SerializedName("clinic_name")
    @Expose
    String clinicName;

    @SerializedName("specialization_his_id")
    @Expose
    String specializationHisId;

    @SerializedName("specialization_name")
    @Expose
    String specializationName;

    @SerializedName("doctor_his_id")
    @Expose
    String doctorHisId;

    @SerializedName("doctor_name")
    @Expose
    String doctorName;


    public RetrofitModal() {
//        String clinicHisId, String clinicName, String specializationHisId, String specializationName, String doctorHisId, String doctorName
        this.clinicHisId = clinicHisId;
        this.clinicName = clinicName;
        this.specializationHisId = specializationHisId;
        this.specializationName = specializationName;
        this.doctorHisId = doctorHisId;
        this.doctorName = doctorName;
    }

    public String getClinicHisId() {
        return clinicHisId;
    }

    public void setClinicHisId(String clinicHisId) {
        this.clinicHisId = clinicHisId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getSpecializationHisId() {
        return specializationHisId;
    }

    public void setSpecializationHisId(String specializationHisId) {
        this.specializationHisId = specializationHisId;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getDoctorHisId() {
        return doctorHisId;
    }

    public void setDoctorHisId(String doctorHisId) {
        this.doctorHisId = doctorHisId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
