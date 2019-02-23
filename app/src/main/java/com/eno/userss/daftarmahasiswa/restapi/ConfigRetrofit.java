package com.eno.userss.daftarmahasiswa.restapi;

import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
        public static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.60.155:8080/server_mahasiswa/index.php/Server/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        public static ApiService service = retrofit.create(ApiService.class);


    }

