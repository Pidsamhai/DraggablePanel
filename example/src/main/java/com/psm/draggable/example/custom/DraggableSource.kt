package com.psm.draggable.example.custom

import android.content.Context
import android.util.AttributeSet
import com.psm.draggable.DraggablePanel
import com.psm.draggable.utils.inflate
import com.psm.draggable.utils.reWidth
import com.psm.draggable.example.R
import kotlinx.android.synthetic.main.layout_top.view.*

class DraggableSource @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : DraggablePanel(context, attrs, defStyleAttr) {

    var mWidthWhenMax = 0

    var mWidthWhenMiddle = 0

    var mWidthWhenMin = 0

    init {
        getFrameFirst().addView(inflate(R.layout.layout_top))
        getFrameSecond().addView(inflate(R.layout.layout_bottom))
    }

    override fun initFrame() {
        mWidthWhenMax = width

        mWidthWhenMiddle = (width - mPercentWhenMiddle * mMarginEdgeWhenMin).toInt()

        mWidthWhenMin = mHeightWhenMinDefault * 22 / 9

        super.initFrame()
    }

    override fun refreshFrameFirst() {
        super.refreshFrameFirst()

        val width = if (mCurrentPercent < mPercentWhenMiddle) {
            (mWidthWhenMax - (mWidthWhenMax - mWidthWhenMiddle) * mCurrentPercent)
        } else {
            (mWidthWhenMiddle - (mWidthWhenMiddle - mWidthWhenMin) * (mCurrentPercent - mPercentWhenMiddle) / (1 - mPercentWhenMiddle))
        }

        frameTop.reWidth(width.toInt())
    }
}