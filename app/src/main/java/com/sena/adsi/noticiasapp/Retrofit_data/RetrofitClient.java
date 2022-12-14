package com.sena.adsi.noticiasapp.Retrofit_data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    //public static final String URL_BASE = "http://{tu_ip}:{tu_puerto}/";
    public static final String URL_BASE = "http://10.0.2.2/retrofit/";
     public static RetrofitApiService getApiService(){
         if (retrofit == null) {
             retrofit = new  Retrofit.Builder()
                     .baseUrl(URL_BASE)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit.create(RetrofitApiService.class);

     }
    }
