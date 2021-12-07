package com.gmail.bishoybasily.recyclerview.adapter

import android.view.ViewGroup
import com.gmail.bishoybasily.recyclerview.item.Item
import com.gmail.bishoybasily.recyclerview.item.ItemLoader
import com.gmail.bishoybasily.recyclerview.viewholder.RecyclerViewViewHolder

/**
 * Created by bishoy on 8/17/17.
 */
abstract class EndlessRecyclerViewAdapter<I : Item, V : RecyclerViewViewHolder<I>> : RecyclerViewAdapter<I, V>() {

    override fun getItemViewType(position: Int): Int {
        if (get(position) is ItemLoader)
            return ItemLoader.TYPE
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        if (viewType == ItemLoader.TYPE)
            return onCreateItemLoaderViewHolder(parent, viewType)
        return super.onCreateViewHolder(parent, viewType)
    }

    abstract fun onCreateItemLoaderViewHolder(parent: ViewGroup, viewType: Int): V

}
