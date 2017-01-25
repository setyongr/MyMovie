package com.setyongr.mymovie.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.setyongr.mymovie.R
import com.setyongr.mymovie.common.loadImg
import com.setyongr.mymovie.data.DataManager
import com.setyongr.mymovie.data.models.MovieResponse
import com.setyongr.mymovie.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import android.app.ProgressDialog
import android.view.MenuItem


class DetailActivity : AppCompatActivity(), DetailMvpView {
    var dialog: ProgressDialog? = null
    val mDetailPresenter: DetailPresenter = DetailPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id= intent.getIntExtra("id", 0)
        mDetailPresenter.attachView(this)
        mDetailPresenter.loadMovie(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailPresenter.detachView()
    }

    override fun showDetail(data: MovieResponse) {
        collapsingToolbar.title = data.title
        tagline.text = data.tagline
        overview.text = data.overview
        releaseDate.text = data.release_date
        budget.text = data.budget.toString()
        backdrop.loadImg("https://image.tmdb.org/t/p/w780" + data.backdrop_path)
        poster.loadImg("https://image.tmdb.org/t/p/w185" + data.poster_path)
        genres.text = ""
        for (a in data.genres) {
            genres.text = "${a.name}, ${genres.text}"
        }
    }

    override fun showError(e: String?) {
        Snackbar.make(coordinator, e ?: "Error", Snackbar.LENGTH_LONG).show()
    }

    override fun showProgress() {
        dialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true)
    }

    override fun hideProgress() {
        dialog?.dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
