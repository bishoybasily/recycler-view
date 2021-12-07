package com.gmail.bishoybasily.sample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.bishoybasily.recyclerview.adapter.EndlessRecyclerViewAdapter
import com.gmail.bishoybasily.recyclerview.viewholder.RecyclerViewViewHolder
import kotlinx.android.synthetic.main.item_thing.view.*

/**
 * Created by bishoy on 1/2/18.
 */

class AdapterThings : EndlessRecyclerViewAdapter<Thing, AdapterThings.AbstractThingViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): AbstractThingViewHolder {
        return ThingViewHolder(
            this,
            LayoutInflater.from(parent.context).inflate(R.layout.item_thing, parent, false)
        )
    }

    override fun onCreateItemLoaderViewHolder(parent: ViewGroup, viewType: Int): AbstractThingViewHolder {
        return ThingLoaderViewHolder(
            this,
            LayoutInflater.from(parent.context).inflate(R.layout.item_loader, parent, false)
        )
    }

    abstract class AbstractThingViewHolder(adapter: AdapterThings, view: View) :
        RecyclerViewViewHolder<Thing>(adapter, view)

    class ThingViewHolder(adapter: AdapterThings, view: View) :
        AbstractThingViewHolder(adapter, view) {

        override fun onBound(i: Thing) {

            itemView.textValue.text = i.name
            itemView.textValue.setTextColor(if (i.selected) Color.RED else Color.BLACK)

            itemView.setOnClickListener {
                i.setSelected(!i.selected)
                itemView.textValue.setTextColor(if (i.selected) Color.RED else Color.BLACK)
            }

            itemView.remove.setOnClickListener {
                adapter.remove(i)
            }
        }

        override fun onRecycled(i: Thing) {

        }
    }

    class ThingLoaderViewHolder(adapter: AdapterThings, view: View) :
        AbstractThingViewHolder(adapter, view)

}


