package com.fidelyo.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class RecyclerViewAdapter<I : RecyclerViewAdapter.Item, V : RecyclerViewViewHolder<I>> : RecyclerView.Adapter<V>() {

    private val items: MutableList<I> = arrayListOf()

    private var clickListener: OnItemClickListener<I>? = null
    private var longClickListener: OnItemLongClickListener<I>? = null

    fun showAll(p0: MutableList<I>) {
        items.clear()
        items.addAll(p0)
        notifyDataSetChanged()
    }

    fun append(p0: MutableList<I>) {
        if (items.addAll(p0))
            notifyItemRangeInserted(items.size - p0.size, p0.size)
    }

    fun append(p0: I) {
        if (items.add(p0))
            notifyItemInserted(items.size - 1)
    }

    fun remove(p0: MutableList<I>) {
        if (items.removeAll(p0))
            notifyItemRangeRemoved(items.size + p0.size, p0.size)
    }

    fun remove(p0: I) {
        if (items.remove(p0))
            notifyItemRemoved(items.size + 1)
    }

    fun get(index: Int): I {
        return items[index]
    }

    fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return Item.TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return onCreateItemViewHolder(parent, viewType).apply {
            this@apply.clickListener = this@RecyclerViewAdapter.clickListener
            this@apply.longClickListener = this@RecyclerViewAdapter.longClickListener
        }
    }

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): V

    final override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(items[position])
    }

    final override fun onViewRecycled(holder: V) {
        holder.recycle()
    }

    final override fun getItemCount() = items.size

    fun onClick(handler: (I, View) -> Unit) {
        clickListener = object : OnItemClickListener<I> {
            override fun onClicked(i: I, view: View) {
                handler(i, view)
            }
        }
    }

    fun onLongClick(handler: (I, View) -> Boolean) {
        longClickListener = object : OnItemLongClickListener<I> {
            override fun onLongClicked(i: I, view: View): Boolean {
                return handler(i, view)
            }
        }
    }

    interface Item {

        companion object {
            val TYPE = 0
        }

    }

    interface OnItemClickListener<in I : Item> {

        fun onClicked(i: I, view: View)

    }

    interface OnItemLongClickListener<in I : Item> {

        fun onLongClicked(i: I, view: View): Boolean

    }

}
