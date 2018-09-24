package com.example.administrator.simplekotlin.data.global

import android.os.AsyncTask
import com.example.administrator.simplekotlin.data.database.AppDatabase
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.data.networks.GithubService
import retrofit2.Call
import java.util.ArrayList
import java.util.concurrent.ExecutionException

class DataManager(private val githubService: GithubService, private val appDatabase: AppDatabase) {

    val allUsers: Call<List<User>>
        get() = githubService.getUsers

    val users: List<User>?
        get() {
            return try {
                GetUsers().execute().get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
                null
            } catch (e: ExecutionException) {
                e.printStackTrace()
                null
            }

        }

    val idFavoritesUsers: List<Int>?
        get() {
            return try {
                GetIdFavoritesUsers().execute().get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
                null
            } catch (e: ExecutionException) {
                e.printStackTrace()
                null
            }

        }

    fun getUser(login: String): Call<User> {
        return githubService.getUser(login)
    }

    fun insertUser(user: User) {
        InsertUser(user).execute()
    }

    fun deleteUser(user: User) {
        DeleteUser(user).execute()
    }

    internal inner class GetUsers : AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg voids: Void): List<User> {
            return appDatabase.userDao().getUsers
        }
    }

    internal inner class GetIdFavoritesUsers : AsyncTask<Void, Void, List<Int>>() {

        override fun doInBackground(vararg voids: Void): List<Int> {
            val favoritesUsers = appDatabase.userDao().getUsers
            val idFavoriteUser = ArrayList<Int>()

            for (favoriteUser in favoritesUsers) idFavoriteUser.add(favoriteUser.id)
            return idFavoriteUser
        }
    }

    internal inner class InsertUser(private val favoriteUser: User) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            appDatabase.userDao().insert(favoriteUser)
            return null
        }
    }

    internal inner class DeleteUser(private val user: User) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            appDatabase.userDao().delete(user)
            return null
        }
    }
}