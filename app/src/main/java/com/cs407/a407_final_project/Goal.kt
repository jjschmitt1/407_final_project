package com.cs407.a407_final_project

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

const val GOAL_COLOR = -0x880088

class Goal(x: Int, y: Int, surfaceWidth: Int, surfaceHeight: Int) {

    // Create wall's rectangle based on location and dimensions
    var rect: Rect = Rect(x, y, x + surfaceWidth, y + surfaceHeight)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

        // Wall color
        paint.color = GOAL_COLOR
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}