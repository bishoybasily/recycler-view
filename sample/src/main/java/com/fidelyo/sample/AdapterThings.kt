package com.fidelyo.sample

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fidelyo.recyclerview.RecyclerViewAdapter
import com.fidelyo.recyclerview.RecyclerViewBindingViewHolder
import com.fidelyo.sample.databinding.ItemThingBinding

/**
 * Created by bishoy on 1/2/18.
 */

class AdapterThings : RecyclerViewAdapter<Thing, AdapterThings.ThingViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_thing, parent, false))
    }

    class ThingViewHolder(val binder: ItemThingBinding) : RecyclerViewBindingViewHolder<Thing, ItemThingBinding>(binder) {

        override fun onAttached(thing: Thing) {
            binder.thing = thing
            binder.executePendingBindings()
        }

        override fun onDetached(thing: Thing) {

        }
    }

}


