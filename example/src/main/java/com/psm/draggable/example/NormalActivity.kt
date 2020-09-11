package com.psm.draggable.example

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.psm.draggable.DraggablePanel
import com.psm.draggable.example.fragment.BottomFragment
import com.psm.draggable.example.fragment.TopFragment
import kotlinx.android.synthetic.main.activity_normal.*

class NormalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        draggablePanel.setDraggableListener(object : DraggablePanel.DraggableListener {
            override fun onExpanded() {
                super.onExpanded()
            }

            override fun onChangeState(state: DraggablePanel.State) {
            }

            override fun onChangePercent(percent: Float) {
                alpha.alpha = 1 - percent
            }

        })

        supportFragmentManager.beginTransaction().add(R.id.frameFirst, TopFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.frameSecond, BottomFragment()).commit()

        btnMax.setOnClickListener { draggablePanel.maximize() }
        btnMin.setOnClickListener { draggablePanel.minimize() }
        btnClose.setOnClickListener { draggablePanel.close() }

    }

    @SuppressLint("SwitchIntDef")
    override fun onConfigurationChanged(newConfig: Configuration) {
        when(newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                draggablePanel.enableFullScreen()
            }

            Configuration.ORIENTATION_PORTRAIT -> {
                draggablePanel.disableFullScreen()
            }
        }
        super.onConfigurationChanged(newConfig)
    }
}
