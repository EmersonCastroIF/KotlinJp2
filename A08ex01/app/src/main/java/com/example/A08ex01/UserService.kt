package com.example.A08ex01

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/jpescola")
    fun getUser(): Call<UserResponse>
}