package com.fidelyo.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class RecyclerViewViewHolder<I : RecyclerViewAdapter.Item>(view: View) : RecyclerView.ViewHolder(view) {

    var onItemClickListener: RecyclerViewAdapter.OnItemClickListener<I>? = null

    lateinit var i: I

    fun bind(i: I) {

        this.i = i
        this.itemView.setOnClickListener { this.onItemClickListener?.onClicked(i, itemView) }

        onAttached(i)
    }

    fun recycle() {
        onDetached(i)
    }

    abstract fun onAttached(i: I)

    abstract fun onDetached(i: I)

}