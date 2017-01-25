package com.setyongr.mymovie.common

import com.setyongr.mymovie.common.adapters.AdapterConstant
import com.setyongr.mymovie.common.adapters.ViewType

data class RedditNews(
        val after: String,
        val before: String,
        val news : List<RedditNewsItem>
)

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType {
    override fun getViewType() = AdapterConstant.NEWS
}