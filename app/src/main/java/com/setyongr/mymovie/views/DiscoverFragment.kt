package com.setyongr.mymovie.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setyongr.mymovie.R
import com.setyongr.mymovie.base.RxBaseFragment
import com.setyongr.mymovie.common.InfiniteScrollListener
import com.setyongr.mymovie.common.inflate
import com.setyongr.mymovie.data.models.DiscoverResultResponse
import com.setyongr.mymovie.presenters.DiscoverPresenter
import com.setyongr.mymovie.views.adapters.DiscoverAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class DiscoverFragment: Fragment(), DiscoverMvpView {

    private var sort_by: String = "popularity.asc"
    private var mDiscoverPresenter: DiscoverPresenter = DiscoverPresenter()

    companion object{
        fun newInstance(sort_by: String): DiscoverFragment {
            val fragment = DiscoverFragment()
            val args = Bundle()
            args.putString("sort_by", sort_by)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = container?.inflate(R.layout.fragment_list)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sort_by = arguments.getString("sort_by")

        mDiscoverPresenter.attachView(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data_list.setHasFixedSize(true)
        val linearLayout = LinearLayoutManager(context)
        data_list.layoutManager = linearLayout
        data_list.clearOnScrollListeners()
        data_list.addOnScrollListener(InfiniteScrollListener({ mDiscoverPresenter.loadDiscover(sort_by)}, linearLayout))

        initAdapter()

        if (savedInstanceState == null) {
            mDiscoverPresenter.loadDiscover(sort_by)
        }
    }


    override fun showList(data: List<DiscoverResultResponse>) {
        (data_list.adapter as DiscoverAdapter).addItem(data)
    }

    override fun showError(e: String?) {
        Snackbar.make(data_list, e ?: "", Snackbar.LENGTH_LONG).show()
    }

    private fun initAdapter() {
        if (data_list.adapter == null) {
            data_list.adapter = DiscoverAdapter()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDiscoverPresenter.detachView()
    }
}
