package com.example.a08ex05.api

import com.example.a08ex05.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Mesma ideia da interface dos slides porem separei pra ficar mais facil a reutilização
interface Endpoint {
    //Passando parametro path que sera recebido ao chamar o metodo
    @GET("api/")
    fun getUsers(
        @Query("results") results: Int
    ): Call<UserResponse>
}