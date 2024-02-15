package com.teamfairy.feature.incomedetail

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.teamfairy.core_ui.util.context.colorOf
import com.teamfairy.feature.R

class IncomeDetailItemDecorator(private val context: Context) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            view.setBackgroundColor(context.colorOf(R.color.main_color))
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            layoutParams.marginStart = 0 // 마진을 16dp로 설정
            view.layoutParams = layoutParams
        }
    }
}
