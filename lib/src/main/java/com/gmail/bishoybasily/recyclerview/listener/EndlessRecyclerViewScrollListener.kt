package com.gmail.bishoybasily.recyclerview.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bishoy on 7/15/17.
 */
abstract class EndlessRecyclerViewScrollListener(
    var visibleThreshold: Int = 4
) : RecyclerView.OnScrollListener() {

    final override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        val manager = recyclerView.layoutManager

        if (manager != null && manager is LinearLayoutManager) {

            val lastVisible = manager.findLastCompletelyVisibleItemPosition()
            val totalItemCount = manager.itemCount

            if (totalItemCount <= lastVisible + visibleThreshold)
                onLoadMore()

        }
    }

    abstract fun onLoadMore()

}