package com.fidelyo.recyclerview

import android.databinding.ViewDataBinding

abstract class RecyclerViewBindingViewHolder<I : RecyclerViewAdapter.Item, B : ViewDataBinding>(adapter: RecyclerViewAdapter<I, *>, b: B) :
        RecyclerViewViewHolder<I>(adapter, b.root)