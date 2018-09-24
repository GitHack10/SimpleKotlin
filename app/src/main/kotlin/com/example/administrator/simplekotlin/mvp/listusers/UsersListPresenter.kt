package com.example.administrator.simplekotlin.mvp.listusers

import com.example.administrator.simplekotlin.data.global.DataManager
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListPresenter(private val dataManager: DataManager) : MvpPresenter<UsersListView>() {

    fun getAllUsers() {

        if (getView() != null) getView().showProgress(true)
        dataManager.allUsers.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && getView() != null) {
                    var users = response.body()
                    var idFavoritesUsers = idFavoritesUsers()
                    getView().showProgress(false)
                    getView().showMessage("done")
                    getView().showUsersList(users!!, idFavoritesUsers)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                if (getView() != null) getView().showMessage("no Network!")
            }
        })
    }

    fun idFavoritesUsers(): List<Int> {
        return dataManager.idFavoritesUsers!!
    }

    fun insertUser(user: User) {
        dataManager.insertUser(user)
    }

    fun itemClick(user: User) {
        if (getView() != null) getView().startInfoActivity(user)
    }
}