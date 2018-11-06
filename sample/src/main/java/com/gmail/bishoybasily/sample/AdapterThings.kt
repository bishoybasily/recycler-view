package com.gmail.bishoybasily.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.gmail.bishoybasily.recyclerview.RecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.RecyclerViewBindingViewHolder
import com.gmail.bishoybasily.sample.databinding.ItemThingBinding
import kotlinx.android.synthetic.main.item_thing.view.*

/**
 * Created by bishoy on 1/2/18.
 */

class AdapterThings :
        RecyclerViewAdapter<Thing, AdapterThings.ThingViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup,
                                        viewType: Int): ThingViewHolder {
        return ThingViewHolder(this, DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_thing, parent, false))
    }

    class ThingViewHolder(adapter: AdapterThings,
                          val binder: ItemThingBinding) :
            RecyclerViewBindingViewHolder<Thing, ItemThingBinding>(adapter, binder) {

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

}


