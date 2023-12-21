package com.example.a08ex03.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkUtils {
    //Equivalente ao metodo static

    //Separei o serviço do Retrofit nessa classe para poder ser reutilizado com mais facilidade
    companion object{
        fun getRetrofitInstance(path: String): Retrofit{
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}