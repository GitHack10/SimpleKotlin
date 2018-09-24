package com.example.administrator.simplekotlin.mvp.global

interface MvpView {

    fun showProgress(show: Boolean)

    fun showMessage(message: String)
}