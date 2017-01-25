package com.setyongr.mymovie.views

import com.setyongr.mymovie.base.MvpView
import com.setyongr.mymovie.data.models.DiscoverResultResponse

interface DiscoverMvpView: MvpView {
    fun showList(data: List<DiscoverResultResponse>)
    fun showError(e: String?)
}