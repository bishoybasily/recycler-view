package com.gmail.bishoybasily.recyclerview

import android.graphics.Rect

/**
 * Created by bishoy on 6/17/17.
 */
class LinearVerticalSpacingItemDecoration(spacing: Int) : SpacingItemDecoration(spacing) {

    override fun applySpacing(position: Int, outRect: Rect) {
        outRect.bottom = spacing
        if (position < 1) {
            outRect.top = spacing
        }
        outRect.left = spacing
        outRect.right = spacing
    }

}