package com.setyongr.mymovie.views.adapters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.setyongr.mymovie.common.RedditNewsItem
import com.setyongr.mymovie.common.adapters.AdapterConstant
import com.setyongr.mymovie.views.adapters.LoadingDelegateAdapter
import com.setyongr.mymovie.common.adapters.ViewType
import com.setyongr.mymovie.common.adapters.ViewTypeDelegateAdapter
import com.setyongr.mymovie.data.models.DiscoverResultResponse
import java.util.*

class DiscoverAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType> = ArrayList()
    private var delegatedAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private var loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstant.LOADING

    }

    init {
        delegatedAdapters.put(AdapterConstant.LOADING, LoadingDelegateAdapter())
        delegatedAdapters.put(AdapterConstant.DISCOVER, DiscoverDelegateAdapter())
        items.add(loadingItem)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatedAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatedAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }
    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(data: List<DiscoverResultResponse>) {
        // remove loading
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // Insert data and loading
        items.addAll(data)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* Plus loading item*/)
    }

    fun clearAndAddItem(data: List<DiscoverResultResponse>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(data)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<DiscoverResultResponse> {
        return items
                .filter { it.getViewType() == AdapterConstant.NEWS }
                .map{ it as DiscoverResultResponse }
    }
    fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}