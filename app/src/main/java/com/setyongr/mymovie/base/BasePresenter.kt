package com.setyongr.mymovie.base

open class BasePresenter<T: MvpView>: Presenter<T> {
    var mMvpView: T? = null
    override fun attachView(mvpView: T) {
        mMvpView = mvpView
    }

    override fun detachView() {
        mMvpView = null
    }

    fun isViewAttached() = mMvpView != null

    fun getMvpView() = mMvpView

    fun checkViewAttached() {
        if (!isViewAttached())
        throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException: RuntimeException("Please call Presenter.attachView(MvpView) before" +
            " requesting data to the Presenter") {
    }


}

