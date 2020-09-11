package com.psm.draggable.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.coordinatorlayout.widget.CoordinatorLayout

class DragFrame @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    var onTouchListener: OnTouchListener? = null

    var isFullScreen = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (isFullScreen) return false
        return if (onTouchListener != null) onTouchListener!!.onInterceptTouchEvent(ev) else super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (onTouchListener != null) onTouchListener!!.onTouchEvent(event) else super.onTouchEvent(event)
    }

    interface OnTouchListener {
        fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
            return false
        }

        fun onTouchEvent(ev: MotionEvent?): Boolean {
            return false
        }
    }
}