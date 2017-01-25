package com.setyongr.mymovie.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.setyongr.mymovie.R
import com.setyongr.mymovie.common.adapters.ViewType
import com.setyongr.mymovie.common.adapters.ViewTypeDelegateAdapter
import com.setyongr.mymovie.common.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup) = LoadingViewHolder(parent)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {

    }

    class LoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_loading)) {
    }

}