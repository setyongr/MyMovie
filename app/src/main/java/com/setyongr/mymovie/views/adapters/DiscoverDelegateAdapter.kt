package com.setyongr.mymovie.views.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.setyongr.mymovie.views.DetailActivity
import com.setyongr.mymovie.R
import com.setyongr.mymovie.common.adapters.ViewType
import com.setyongr.mymovie.common.adapters.ViewTypeDelegateAdapter
import com.setyongr.mymovie.common.inflate
import com.setyongr.mymovie.common.loadImg
import com.setyongr.mymovie.data.models.DiscoverResponse
import com.setyongr.mymovie.data.models.DiscoverResultResponse
import kotlinx.android.synthetic.main.discover_item.view.*

class DiscoverDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        (holder as DiscoverViewHolder).bind(item as DiscoverResultResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup) = DiscoverViewHolder(parent)

    class DiscoverViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.discover_item)) {
        fun bind(item: DiscoverResultResponse) = with(itemView) {
            banner.loadImg("https://image.tmdb.org/t/p/w780" + item.backdrop_path)
            title.text = item.title
            release.text = item.release_date
            setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", item.id)
                context.startActivity(intent)
            }
        }
    }
}

