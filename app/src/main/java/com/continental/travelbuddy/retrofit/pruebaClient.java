package com.continental.travelbuddy.retrofit;

import com.continental.travelbuddy.comun.constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pruebaClient {

    private static pruebaClient instance = null;
    private pruebaServicio pruebaServicio;
    private Retrofit retrofit;

    public pruebaClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(constantes.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pruebaServicio = retrofit.create(pruebaServicio.class);
    }

    public static pruebaClient getInstance(){
        if(instance == null){
            instance = new pruebaClient();
        }
        return instance;
    }

    public  pruebaServicio getPruebaServicio(){
        return pruebaServicio;
    }
}
