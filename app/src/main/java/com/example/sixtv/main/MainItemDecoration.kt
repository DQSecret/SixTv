package com.example.sixtv.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtv.ext.dp

class MainItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        val right = 39.dp
        val bottom = 40.dp
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos: Int = parent.getChildAdapterPosition(view)
        when (pos % 3) {
            0, 1 -> {
                outRect.right = right
            }
            2 -> {
                outRect.right = 0
            }
        }
        outRect.bottom = bottom
    }
}