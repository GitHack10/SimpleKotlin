package com.example.administrator.simplekotlin

import android.app.Application
import android.arch.persistence.room.Room
import com.example.administrator.simplekotlin.data.database.AppDatabase
import com.example.administrator.simplekotlin.data.global.DataManager
import com.example.administrator.simplekotlin.data.networks.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {

        private const val BASE_URL = "https://api.github.com/"

        lateinit var dataManager: DataManager
    }

    override fun onCreate() {
        super.onCreate()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()

        val githubService = retrofit.create(GithubService::class.java)

        val appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()

        dataManager = DataManager(githubService, appDatabase)
    }
}