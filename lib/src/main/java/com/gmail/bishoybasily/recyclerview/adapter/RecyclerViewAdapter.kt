package com.gmail.bishoybasily.recyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.bishoybasily.recyclerview.item.Item
import com.gmail.bishoybasily.recyclerview.viewholder.RecyclerViewViewHolder

abstract class RecyclerViewAdapter<I : Item, V : RecyclerViewViewHolder<I>> :
    RecyclerView.Adapter<V>() {

    private val _items = ArrayList<I>()

    val items: List<I>
        get() = _items

    private var clickListener: OnItemClickListener<I>? = null
    private var longClickListener: OnItemLongClickListener<I>? = null

    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    fun show(p0: Collection<I>): RecyclerViewAdapter<I, V> {
        _items.clear()
        _items.addAll(p0)
        notifyItemRangeInserted(0, itemCount)
        return this
    }

    fun append(p0: Collection<I>): RecyclerViewAdapter<I, V> {
        if (_items.addAll(p0))
            notifyItemRangeInserted(itemCount - p0.size, p0.size)
        return this
    }

    fun append(p0: I): RecyclerViewAdapter<I, V> {
        if (_items.add(p0))
            notifyItemInserted(itemCount - 1)
        return this
    }

    fun remove(position: Int): RecyclerViewAdapter<I, V> {
        if (position in 0 until itemCount) {
            _items.removeAt(position)
            notifyItemRemoved(position)
        }
        return this
    }

    fun remove(p0: I): RecyclerViewAdapter<I, V> {
        val position = _items.indexOf(p0)
        remove(position)
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

    fun scrollToTheEnd(): RecyclerViewAdapter<I, V> {
        recyclerView?.scrollToPosition(itemCount - 1)
        return this
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
        holder.bind(_items[position])
    }

    final override fun onViewRecycled(holder: V) {
        holder.recycle()
    }

    final override fun getItemCount() = _items.size

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

    interface OnItemClickListener<in I : Item> {

        fun onClicked(i: I, view: View)

    }

    interface OnItemLongClickListener<in I : Item> {

        fun onLongClicked(i: I, view: View): Boolean

    }

}
