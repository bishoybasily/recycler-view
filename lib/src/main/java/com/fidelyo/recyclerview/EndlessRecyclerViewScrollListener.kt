package com.fidelyo.recyclerview

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by bishoy on 7/15/17.
 */

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var applicable = false
        var lastVisible = 0
        val visibleThreshold = 3

        val totalItemCount = recyclerView.layoutManager.itemCount

        if (recyclerView.layoutManager is GridLayoutManager) {
            applicable = true
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
        }

        if (recyclerView.layoutManager is LinearLayoutManager) {
            applicable = true
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
        }

        if (applicable && totalItemCount <= lastVisible + visibleThreshold) {
            recyclerView.post { this.onLoadMore() }
        }
    }

    abstract fun onLoadMore()

}