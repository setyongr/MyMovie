package com.setyongr.mymovie.views

import com.setyongr.mymovie.base.MvpView
import com.setyongr.mymovie.data.models.DiscoverResultResponse
import com.setyongr.mymovie.data.models.MovieResponse

interface DetailMvpView: MvpView {
    fun showDetail(data: MovieResponse)
    fun showError(e: String?)
    fun showProgress()
    fun hideProgress()
}