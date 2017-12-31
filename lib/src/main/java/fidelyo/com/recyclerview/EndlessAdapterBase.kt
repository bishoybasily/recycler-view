package com.fidelyo.base.adapter.recycler_view

import android.view.ViewGroup
import fidelyo.com.recyclerview.AdapterBase
import fidelyo.com.recyclerview.ViewHolderBase

/**
 * Created by bishoy on 8/17/17.
 */

abstract class EndlessAdapterBase<I : AdapterBase.Item, V : ViewHolderBase<I>> : AdapterBase<I, V>() {

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
