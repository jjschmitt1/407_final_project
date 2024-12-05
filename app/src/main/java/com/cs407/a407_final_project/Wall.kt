package com.cs407.a407_final_project

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

const val WALL_COLOR = -0x00888888

class Wall(x: Int, y: Int, surfaceWidth: Int, surfaceHeight: Int) {

    // Create wall's rectangle based on location and dimensions
    var rect: Rect = Rect(x, y, x + surfaceWidth, y + surfaceHeight)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

        // Wall color
        paint.color = WALL_COLOR
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}