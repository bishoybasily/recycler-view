package com.fidelyo.recyclerview

import android.databinding.ViewDataBinding

abstract class RecyclerViewBindingViewHolder<I : RecyclerViewAdapter.Item, B : ViewDataBinding>(b: B) : RecyclerViewViewHolder<I>(b.root)