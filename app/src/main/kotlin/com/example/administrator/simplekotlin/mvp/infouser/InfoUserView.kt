package com.example.administrator.simplekotlin.mvp.infouser

import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpView

interface InfoUserView : MvpView {

    fun showInfoUser(user: User)
}