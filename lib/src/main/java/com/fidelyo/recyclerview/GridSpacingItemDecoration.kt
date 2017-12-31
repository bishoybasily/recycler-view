package com.fidelyo.recyclerview

import android.graphics.Rect

/**
 * Created by bishoy on 6/17/17.
 */
class GridSpacingItemDecoration(spacing: Int, val columns: Int) : SpacingItemDecoration(spacing) {

    var spanLookup = object : SpanLookup {
        override fun isSpan(position: Int) = false
    }

    override fun applySpacing(index: Int, rect: Rect) {

        val column = index % columns // item column

        if (index < columns) { // top edge
            rect.top = spacing
        }
        rect.bottom = spacing // item bottom
        rect.left = spacing - column * (spacing / columns) // spacing - column * ((1f / columns) * spacing)
        rect.right = (column + 1) * (spacing / columns) // (column + 1) * ((1f / columns) * spacing)

    }

    interface SpanLookup {
        fun isSpan(position: Int): Boolean
    }
}