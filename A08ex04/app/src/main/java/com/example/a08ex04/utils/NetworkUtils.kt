package com.example.a08ex04.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkUtils {
    //Equivalente ao metodo static

    //Serviço Retrofit
    companion object{
        fun getRetrofitInstance(path: String): Retrofit{
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}