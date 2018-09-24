package com.example.administrator.simplekotlin.mvp.favoritesusers

import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpView

interface FavoritesUsersListView : MvpView {

    fun showFavoritesUsers(favoritesUsers: List<User>)

    fun startInfoActivity(user: User)
}