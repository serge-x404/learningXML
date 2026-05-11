package dev.serge.learningxml

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CategoryItemDecoration(
    private val spacingDp: Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = (spacingDp * view.context.resources.displayMetrics.density).toInt()
        val position = parent.getChildAdapterPosition(view)

        outRect.left = spacing
        outRect.right = spacing
        outRect.bottom = spacing

        if (position < 3) {
            outRect.top = spacing
        }
    }
}