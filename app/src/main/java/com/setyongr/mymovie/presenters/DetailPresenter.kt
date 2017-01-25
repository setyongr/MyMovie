package com.setyongr.mymovie.presenters

import android.support.design.widget.Snackbar
import com.setyongr.mymovie.base.BasePresenter
import com.setyongr.mymovie.common.loadImg
import com.setyongr.mymovie.data.DataManager
import com.setyongr.mymovie.data.models.DiscoverResponse
import com.setyongr.mymovie.data.models.MovieResponse
import com.setyongr.mymovie.views.DetailMvpView
import com.setyongr.mymovie.views.DiscoverMvpView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_list.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import android.app.ProgressDialog



class DetailPresenter: BasePresenter<DetailMvpView>() {
    var subscription: Subscription? = null
    var discover: DiscoverResponse? = null

    override fun attachView(mvpView: DetailMvpView) {
        super.attachView(mvpView)
    }


    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }


    fun loadMovie(id: Int) {
        checkViewAttached()
        if (subscription != null && !(subscription as Subscription).isUnsubscribed()) {
            (subscription as Subscription).unsubscribe();
        }

        getMvpView()?.showProgress()
        subscription =  DataManager.getMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            receivedItem ->
                            getMvpView()?.showDetail(receivedItem)
                            getMvpView()?.hideProgress()
                        },
                        {
                            e -> getMvpView()?.showError(e.message)
                        }
                )

    }
}
