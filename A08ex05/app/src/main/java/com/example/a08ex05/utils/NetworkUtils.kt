package com.example.a08ex05.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkUtils {
    //Equivalente ao metodo static

    //Serviço
    companion object{
        fun getRetrofitInstance(path: String): Retrofit{
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}