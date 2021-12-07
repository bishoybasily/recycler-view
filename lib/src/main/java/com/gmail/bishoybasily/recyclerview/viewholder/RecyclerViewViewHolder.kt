package com.gmail.bishoybasily.recyclerview.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.adapter.RecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.item.Item

abstract class RecyclerViewViewHolder<I : Item>(
    val adapter: RecyclerViewAdapter<I, *>,
    val view: View
) : RecyclerView.ViewHolder(view) {

    var clickListener: RecyclerViewAdapter.OnItemClickListener<I>? = null
    var longClickListener: RecyclerViewAdapter.OnItemLongClickListener<I>? = null

    lateinit var i: I

    fun bind(i: I) {

        this.i = i

        val click = clickListener
        if (click != null)
            this.itemView.setOnClickListener { click.onClicked(i, itemView) }

        val longClick = longClickListener
        if (longClick != null)
            this.itemView.setOnLongClickListener { return@setOnLongClickListener longClick.onLongClicked(i, itemView) }

        onAttached(i)
    }

    fun recycle() {
        onDetached(i)
    }

    open fun onAttached(i: I) {

    }

    open fun onDetached(i: I) {

    }

    fun remove() {
        adapter.remove(i)
    }

}