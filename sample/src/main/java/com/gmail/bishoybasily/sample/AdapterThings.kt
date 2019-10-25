package com.gmail.bishoybasily.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gmail.bishoybasily.recyclerview.EndlessRecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.RecyclerViewViewHolder
import com.gmail.bishoybasily.sample.databinding.ItemLoaderBinding
import com.gmail.bishoybasily.sample.databinding.ItemThingBinding
import kotlinx.android.synthetic.main.item_thing.view.*

/**
 * Created by bishoy on 1/2/18.
 */

class AdapterThings : EndlessRecyclerViewAdapter<Thing, AdapterThings.AbstractThingViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup): AbstractThingViewHolder {
        val binder: ItemThingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_thing, parent, false)
        return ThingViewHolder(this, binder)
    }

    override fun onCreateItemLoaderViewHolder(parent: ViewGroup): AbstractThingViewHolder {
        val binder: ItemLoaderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_loader, parent, false)
        return ThingLoaderViewHolder(this, binder)
    }

    abstract class AbstractThingViewHolder(adapter: AdapterThings, binder: ViewDataBinding) :
            RecyclerViewViewHolder<Thing>(adapter, binder.root)

    class ThingViewHolder(adapter: AdapterThings, val binder: ItemThingBinding) :
            AbstractThingViewHolder(adapter, binder) {

        override fun onAttached(i: Thing) {

            binder.thing = i
            binder.executePendingBindings()

            itemView.remove.setOnClickListener {
                adapter.remove(i)
            }
        }

        override fun onDetached(i: Thing) {

        }
    }

    class ThingLoaderViewHolder(adapter: AdapterThings, val binder: ItemLoaderBinding) :
            AbstractThingViewHolder(adapter, binder)

}


