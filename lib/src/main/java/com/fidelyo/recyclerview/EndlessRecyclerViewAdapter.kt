package com.fidelyo.recyclerview

import android.view.ViewGroup

/**
 * Created by bishoy on 8/17/17.
 */

abstract class EndlessRecyclerViewAdapter<I : RecyclerViewAdapter.Item, V : RecyclerViewViewHolder<I>> : RecyclerViewAdapter<I, V>() {

    override fun getItemViewType(position: Int): Int {
        if (items[position] is ItemLoader)
            return ItemLoader.TYPE
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        when (viewType) {
            ItemLoader.TYPE -> return onCreateItemLoaderViewHolder(parent, viewType)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    abstract fun onCreateItemLoaderViewHolder(parent: ViewGroup, viewType: Int): V

    interface ItemLoader : Item {

        companion object {
            val TYPE = 1
        }

    }

}
