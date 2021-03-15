package com.example.tashafinativeandroid.remote;

import com.example.tashafinativeandroid.MainInterface;

public class HISAPIUtils {
    public HISAPIUtils() {
    }

    public static final String BASE_URL="https://eu.test.connect.boomi.com/ws/rest/healthcare/healthhub/v1/";
    public static MainInterface getApiService(){
        return RetrofitClient2.getClient(BASE_URL).create(MainInterface.class);
    }
}
