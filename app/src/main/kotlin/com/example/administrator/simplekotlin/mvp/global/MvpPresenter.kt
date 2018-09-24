package com.example.administrator.simplekotlin.mvp.global

open class MvpPresenter<Type : MvpView> {

    private lateinit var view: Type

    fun attachView(view: Type) {
        this.view = view
    }

    fun detechView() {
        this.view = view
    }

    protected fun getView(): Type {
        return view
    }
}