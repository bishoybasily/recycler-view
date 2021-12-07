package com.gmail.bishoybasily.recyclerview.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.adapter.RecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.item.Item

abstract class RecyclerViewViewHolder<I : Item>(
    val adapter: RecyclerViewAdapter<I, *>,
    view: View
) : RecyclerView.ViewHolder(view) {

    lateinit var i: I

    fun bind(i: I) {

        this.i = i

        onBound(i)
    }

    fun recycle() {
        onRecycled(i)
    }

    open fun onBound(i: I) {

    }

    open fun onRecycled(i: I) {

    }


}