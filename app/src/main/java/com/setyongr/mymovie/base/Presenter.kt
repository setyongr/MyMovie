package com.setyongr.mymovie.base

interface Presenter<in V: MvpView>{
    fun attachView(mvpView: V)
    fun detachView()
}