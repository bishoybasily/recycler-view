package com.gmail.bishoybasily.recyclerview

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewViewHolder<I : RecyclerViewAdapter.Item>(val adapter: RecyclerViewAdapter<I, *>,
                                                                    val view: View) :
        RecyclerView.ViewHolder(view),
        LifecycleOwner {

    var clickListener: RecyclerViewAdapter.OnItemClickListener<I>? = null
    var longClickListener: RecyclerViewAdapter.OnItemLongClickListener<I>? = null

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
    }

    lateinit var i: I

    fun bind(i: I) {

        this.i = i

        val click = clickListener
        if (click != null)
            this.itemView.setOnClickListener { click.onClicked(i, itemView) }

        val longClick = longClickListener
        if (longClick != null)
            this.itemView.setOnLongClickListener { return@setOnLongClickListener longClick.onLongClicked(i, itemView) }

        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        onAttached(i)
    }

    fun recycle() {
        onDetached(i)
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    abstract fun onAttached(i: I)

    abstract fun onDetached(i: I)

    fun remove() {
        adapter.remove(i)
    }

    override fun getLifecycle() = lifecycleRegistry

}