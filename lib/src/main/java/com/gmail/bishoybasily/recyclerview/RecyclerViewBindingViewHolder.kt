package com.gmail.bishoybasily.recyclerview

import androidx.databinding.ViewDataBinding

abstract class RecyclerViewBindingViewHolder<I : RecyclerViewAdapter.Item, B : ViewDataBinding>(adapter: RecyclerViewAdapter<I, *>, b: B) :
        RecyclerViewViewHolder<I>(adapter, b.root)