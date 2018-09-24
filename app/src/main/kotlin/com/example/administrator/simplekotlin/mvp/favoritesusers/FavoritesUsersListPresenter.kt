package com.example.administrator.simplekotlin.mvp.favoritesusers

import com.example.administrator.simplekotlin.data.global.DataManager
import com.example.administrator.simplekotlin.data.models.User
import com.example.administrator.simplekotlin.mvp.global.MvpPresenter

class FavoritesUsersListPresenter(private val dataManager: DataManager) : MvpPresenter<FavoritesUsersListView>() {

    fun getFavoritesUsers() {
        if (getView() != null) getView().showProgress(true)
        getView().showProgress(false)
        getView().showFavoritesUsers(dataManager.users!!)
    }

    fun deleteUser(favoriteUser: User) {
        dataManager.deleteUser(favoriteUser)
        getView().showFavoritesUsers(dataManager.users!!)
    }

    fun itemClick(user: User) {
        if (getView() != null) getView().startInfoActivity(user)
    }
}