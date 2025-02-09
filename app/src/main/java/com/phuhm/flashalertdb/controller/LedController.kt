package com.phuhm.flashalertdb.controller

import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View

class LedController {

    private var animator: ValueAnimator? = null

    fun startSmoothFlashing(view: View) {
        val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE)
        animator = ValueAnimator.ofFloat(0f, 3f).apply {
            duration = 500
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART

            addUpdateListener { animation ->
                val fraction = animation.animatedValue as Float
                val index = fraction.toInt() % colors.size
                val nextIndex = (index + 1) % colors.size
                val blendRatio = fraction - index

                val color = blendColors(colors[index], colors[nextIndex], blendRatio)
                view.setBackgroundColor(color)
            }
        }
        animator?.start()
    }

    fun stopFlashing() {
        animator?.cancel()
    }

    private fun blendColors(color1: Int, color2: Int, ratio: Float): Int {
        val inverseRatio = 1 - ratio
        val r = (Color.red(color1) * inverseRatio + Color.red(color2) * ratio).toInt()
        val g = (Color.green(color1) * inverseRatio + Color.green(color2) * ratio).toInt()
        val b = (Color.blue(color1) * inverseRatio + Color.blue(color2) * ratio).toInt()
        return Color.rgb(r, g, b)
    }
}
