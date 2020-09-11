package com.psm.draggable.example

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.psm.draggable.DraggablePanel
import com.psm.draggable.utils.toPx
import com.psm.draggable.example.fragment.BottomFragment
import com.psm.draggable.example.fragment.TopFragment
import com.psm.draggable.utils.gone
import com.psm.draggable.utils.visible
import kotlinx.android.synthetic.main.activity_custom.*
import kotlinx.android.synthetic.main.layout_bottom.*
import kotlin.math.max
import kotlin.math.min

class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)

        draggablePanel.setDraggableListener(object : DraggablePanel.DraggableListener {
            override fun onExpanded() {
                super.onExpanded()
            }

            override fun onChangeState(state: DraggablePanel.State) {
            }

            override fun onChangePercent(percent: Float) {
                alpha.alpha = 1 - percent
                shadow.alpha = percent
            }

        })

        draggablePanel.setNavigationLayout(navigation)

        btnHideNav.setOnClickListener{
            if (navigation.visibility == View.VISIBLE) {
                navigation.gone()
            } else {
                navigation.visible()
            }
        }

        supportFragmentManager.beginTransaction().add(R.id.frameTop, TopFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.frameBottom, BottomFragment()).commit()

        btnMax.setOnClickListener { draggablePanel.maximize() }
        btnMin.setOnClickListener { draggablePanel.minimize() }
        btnClose.setOnClickListener { draggablePanel.close() }
        btnSetHeightMax.setOnClickListener {
            var heightMax = 0
            if (etHeightMax.text.isNotEmpty()) {
                heightMax = etHeightMax.text.toString().toInt()
            }
            heightMax = max(heightMax, 200)
            heightMax = min(heightMax, 400)

            draggablePanel.setHeightMax(heightMax.toPx())
        }

    }
}
