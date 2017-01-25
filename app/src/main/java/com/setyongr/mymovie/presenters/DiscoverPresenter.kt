package com.setyongr.mymovie.presenters

import android.support.design.widget.Snackbar
import com.setyongr.mymovie.base.BasePresenter
import com.setyongr.mymovie.data.DataManager
import com.setyongr.mymovie.data.models.DiscoverResponse
import com.setyongr.mymovie.views.DiscoverMvpView
import kotlinx.android.synthetic.main.fragment_list.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class DiscoverPresenter: BasePresenter<DiscoverMvpView>() {
    var subscription: Subscription? = null
    var discover: DiscoverResponse? = null

    override fun attachView(mvpView: DiscoverMvpView) {
        super.attachView(mvpView)
    }


    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }


    fun loadDiscover(sort_by: String) {
        checkViewAttached()
        if (subscription != null && !(subscription as Subscription).isUnsubscribed()) {
            (subscription as Subscription).unsubscribe();
        }

        val currentDateTimeString = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        subscription = DataManager.getDiscover(discover?.page?.plus(1) ?: 1, sort_by, currentDateTimeString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            receivedItem ->
                            discover = receivedItem
                            getMvpView()?.showList(receivedItem.results)

                        },
                        {
                            e -> getMvpView()?.showError(e.message)

                        }
                )

    }
}
