package com.psm.draggable.widget

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.psm.draggable.utils.reHeight

class DragBehavior(private val frameSecond: View, private val isFullScreen: Boolean = false) : CoordinatorLayout.Behavior<View>() {

    override fun layoutDependsOn(parent: CoordinatorLayout, fab: View, dependency: View): Boolean {
        return true
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (!isFullScreen) child.reHeight(frameSecond.y.toInt())
        return true
    }

}