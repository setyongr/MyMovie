package com.setyongr.mymovie.data.models

import com.setyongr.mymovie.common.adapters.AdapterConstant
import com.setyongr.mymovie.common.adapters.ViewType

class DiscoverResponse (
        val page: Int,
        val results: List<DiscoverResultResponse>
)
class DiscoverResultResponse(
        val poster_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String,
        val genre_id: List<Int>,
        val id: Int,
        val original_title: String,
        val original_language: String,
        val title: String,
        val backdrop_path: String,
        val popularity: Float,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Float
): ViewType {
    override fun getViewType(): Int {
        return AdapterConstant.DISCOVER
    }
}

class MovieResponse (
        val adult: Boolean,
        val backdrop_path: String,
        val budget: Int,
        val genres: List<IdName>,
        val homepage: String,
        val id: Int,
        val imdb_id: String,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Float,
        val poster_path: String,
        val production_companies: List<IdName>,
        val release_date: String,
        val status: String,
        val tagline: String,
        val title: String,
        val video: Boolean,
        val vote_average: Float,
        val vote_count: Int
)

class IdName(
        val id: Int,
        val name: String
)