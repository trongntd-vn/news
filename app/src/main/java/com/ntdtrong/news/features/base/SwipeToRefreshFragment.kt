package com.ntdtrong.news.features.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

abstract class SwipeToRefreshFragment: Fragment() {
    abstract val swipeLayout: SwipeRefreshLayout
    abstract fun refreshData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeToRefresh()
    }

    private fun setupSwipeToRefresh() {
        swipeLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        swipeLayout.setOnRefreshListener {
            refreshData()
        }
    }
}