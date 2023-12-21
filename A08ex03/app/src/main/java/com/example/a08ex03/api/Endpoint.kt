package com.example.a08ex03.api

import com.example.a08ex03.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//Mesma ideia da interface dos slides porem separei pra ficar mais facil a reutilização
interface Endpoint {
    //Passando parametro path que sera recebido ao chamar o metodo
    @GET("users/{usuario}")
    fun getUsers(
        @Path("usuario") usuario: String
    ): Call<User>
}