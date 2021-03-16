package com.example.tashafinativeandroid.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {
    public static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrl){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
//                        .header("User-Agent", "Your-App-Name")
                        .header("Accept", "application/json")
                        .header("Authorization", "Basic dGFzaGFmaXRlc3RxYUBhbGZ1dHRhaW0tUFhDWE9ELkFFMVk4RTo2YmI1YjkxOC1jN2JmLTQ5YWQtOTU3Yi04ZjM0NjU5NzkxN2I=")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }

        }).addInterceptor(loggingInterceptor);

        OkHttpClient client = httpClient.build();

        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
