package com.setyongr.mymovie.data

import com.setyongr.mymovie.data.models.DiscoverResponse
import com.setyongr.mymovie.data.models.MovieResponse
import rx.Observable

object DataManager {
    val movieService = MovieService.Creator.newService()
    fun getDiscover(page: Int = 1, sort_by: String, lte: String): Observable<DiscoverResponse>
            = movieService.getDiscover(sort_by, page, lte)

    fun getMovie(id: Int): Observable<MovieResponse> = movieService.getMovie(id)


}