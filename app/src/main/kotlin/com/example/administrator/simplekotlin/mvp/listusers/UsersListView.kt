package com.example.administrator.simplekotlin.mvp.listusers

import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpView

interface UsersListView : MvpView {

    fun showUsersList(users: List<User>, idFavoritesUsers: List<Int>)

    fun startInfoActivity(user: Any)
}