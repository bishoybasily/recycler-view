package com.fidelyo.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class RecyclerViewAdapter<I : RecyclerViewAdapter.Item, V : RecyclerViewViewHolder<I>> : RecyclerView.Adapter<V>() {

    var items: ArrayList<I> = arrayListOf()

    var onItemClickListener: OnItemClickListener<I>? = null

    fun showAll(p0: ArrayList<I>) {
        items.clear()
        items.addAll(p0)
        notifyDataSetChanged()
    }

    fun append(p0: ArrayList<I>) {
        items.addAll(p0)
        notifyItemRangeInserted(items.size - p0.size, p0.size)
    }

    fun append(p0: I) {
        val position = items.size
        if (items.add(p0))
            notifyItemInserted(position)
    }

    fun remove(p0: I) {
        val position = items.indexOf(p0)
        if (items.remove(p0))
            notifyItemRemoved(position)
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
            if (this@RecyclerViewAdapter.onItemClickListener != null)
                onItemClickListener = this@RecyclerViewAdapter.onItemClickListener
        }
    }

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): V

    override final fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(items[position])
    }

    override final fun onViewRecycled(holder: V) {
        holder.recycle()
    }

    override final fun getItemCount() = items.size

    interface Item {

        companion object {
            val TYPE = 0
        }

    }

    interface OnItemClickListener<I : Item> {

        fun onClicked(i: I, view: View)

    }
}
