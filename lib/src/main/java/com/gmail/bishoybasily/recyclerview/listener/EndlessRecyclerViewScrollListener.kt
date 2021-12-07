package com.gmail.bishoybasily.recyclerview.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bishoy on 7/15/17.
 */

abstract class EndlessRecyclerViewScrollListener(var visibleThreshold: Int = 4) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        super.onScrolled(recyclerView, dx, dy)

        val manager = recyclerView.layoutManager

        if (manager != null) {

            if (manager is LinearLayoutManager) {

                val layoutManager = manager
                val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()

                val totalItemCount = manager.itemCount

                if (totalItemCount <= lastVisible + visibleThreshold)
                    onLoadMore()

            }

        }
    }

    abstract fun onLoadMore()

}