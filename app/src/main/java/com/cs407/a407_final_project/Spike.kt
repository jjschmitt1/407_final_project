package com.cs407.a407_final_project

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

const val SPIKE_COLOR = -0x0000ffff

/**
 * Defines a spike that
 */
class Spike(x: Float, y: Float, length: Int, rotation: Int) {

    var spike: Path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        spike.moveTo(x, y)
        if (rotation == 90) {
            spike.lineTo(x, y + length)
            spike.lineTo(x + length, y + (length / 2))
            spike.lineTo(x, y)
        } else if (rotation == 180) {
            spike.lineTo(x - length, y)
            spike.lineTo(x - (length / 2), y + length)
            spike.lineTo(x, y)
        } else if (rotation == 270) {
            spike.lineTo(x, y - length)
            spike.lineTo(x - length, y - (length / 2))
            spike.lineTo(x, y)
        } else {
            spike.lineTo(x + length, y)
            spike.lineTo(x + (length / 2), y - length)
            spike.lineTo(x, y)
        }

        // Wall color
        paint.color = SPIKE_COLOR
    }

    fun draw(canvas: Canvas) {
        canvas.drawPath(spike, paint)
    }
}