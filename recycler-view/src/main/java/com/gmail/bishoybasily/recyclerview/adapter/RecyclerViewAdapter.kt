package com.gmail.bishoybasily.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.item.Item
import com.gmail.bishoybasily.recyclerview.viewholder.RecyclerViewViewHolder

abstract class RecyclerViewAdapter<I : Item, V : RecyclerViewViewHolder<I>> :
    RecyclerView.Adapter<V>() {

    private val _items = ArrayList<I>()

    val items: List<I>
        get() = _items

    fun append(p0: I): RecyclerViewAdapter<I, V> {
        if (_items.add(p0))
            notifyItemInserted(itemCount - 1)
        return this
    }

    fun append(p0: Collection<I>): RecyclerViewAdapter<I, V> {
        if (_items.addAll(p0))
            notifyItemRangeInserted(itemCount - p0.size, p0.size)
        return this
    }

    fun appendOnce(p0: I): RecyclerViewAdapter<I, V> {
        if (!_items.contains(p0))
            return append(p0)
        return this
    }

    fun remove(p0: I): RecyclerViewAdapter<I, V> {
        val position = _items.indexOf(p0)
        if (_items.remove(p0))
            notifyItemRemoved(position)
        return this
    }

    fun removeOnce(p0: I): RecyclerViewAdapter<I, V> {
        if (_items.contains(p0))
            return remove(p0)
        return this
    }

    fun get(index: Int): I {
        return _items[index]
    }

    fun clear(): RecyclerViewAdapter<I, V> {
        _items.clear()
        notifyItemRangeRemoved(0, itemCount)
        return this
    }

    override fun getItemViewType(position: Int): Int {
        return Item.TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return onCreateItemViewHolder(parent, viewType)
    }

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): V

    final override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(_items[position])
    }

    final override fun onViewRecycled(holder: V) {
        holder.recycle()
    }

    final override fun getItemCount() = _items.size

}
