package com.example.administrator.simplekotlin.data.networks

import com.example.administrator.simplekotlin.data.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @get:GET("users")
    val getUsers: Call<List<User>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<User>
}