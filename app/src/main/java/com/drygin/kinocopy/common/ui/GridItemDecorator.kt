package com.drygin.kinocopy.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class GridItemDecoration(
    private val spacing: Int,
    private val edgeSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % 2
        outRect.left = if (column == 0) edgeSpacing else spacing / 2
        outRect.right = if (column == 0) spacing / 2 else edgeSpacing
        outRect.top = spacing
    }
}
