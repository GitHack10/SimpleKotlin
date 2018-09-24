package com.example.administrator.simplekotlin.mvp.infouser

import com.example.administrator.simplekotlin.data.global.DataManager
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoUserPresenter(private val dataManager: DataManager) : MvpPresenter<InfoUserView>() {

    fun getUser(login: String) {

        if (getView() != null) getView().showProgress(true)
        dataManager.getUser(login).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful()) {
                    val user = response.body()
                    getView().showProgress(false)
                    getView().showInfoUser(user!!)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })
    }
}