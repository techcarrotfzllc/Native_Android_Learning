package com.example.tashafinativeandroid.remote;

import com.example.tashafinativeandroid.WpApiInterface;

public class WPAPIUtils {
    public WPAPIUtils() {
    }

    public static final String BASE_URL="http://demo.techcarrot.ae/tashafidev/wp-json/tashafiapidata/";
    public static WpApiInterface getApiService(){
        return RetrofitClient1.getClient(BASE_URL).create(WpApiInterface.class);
    }
}
