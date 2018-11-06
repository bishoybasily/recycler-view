package com.gmail.bishoybasily.recyclerview

import android.graphics.Rect

/**
 * Created by bishoy on 6/17/17.
 */
class GridSpacingItemDecoration(spacing: Int, val columns: Int) : SpacingItemDecoration(spacing) {

    var spanLookup = object : SpanLookup {
        override fun isSpan(position: Int) = false
    }

    override fun applySpacing(position: Int, outRect: Rect) {

        val column = position % columns // item column

        if (position < columns) { // top edge
            outRect.top = spacing
        }

        outRect.bottom = spacing // item bottom
        outRect.left = spacing - column * (spacing / columns) // spacing - column * ((1f / columns) * spacing)
        outRect.right = (column + 1) * (spacing / columns) // (column + 1) * ((1f / columns) * spacing)

    }

    interface SpanLookup {
        fun isSpan(position: Int): Boolean
    }
}