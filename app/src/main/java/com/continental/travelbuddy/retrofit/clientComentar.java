package com.continental.travelbuddy.retrofit;

import com.continental.travelbuddy.comun.constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class clientComentar {
    private static clientComentar instance = null;
    private pruebaServicio pruebaServicio;
    private Retrofit retrofit;

    public clientComentar() {
        retrofit = new Retrofit.Builder()
                .baseUrl(constantes.URL_COMENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pruebaServicio = retrofit.create(pruebaServicio.class);
    }

    public static clientComentar getInstance(){
        if(instance == null){
            instance = new clientComentar();
        }
        return instance;
    }

    public  pruebaServicio getPruebaServicio(){
        return pruebaServicio;
    }
}
