package com.cs407.a407_final_project

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat


class Wall(x: Int, y: Int, surfaceWidth: Int, surfaceHeight: Int, context: Context) {

    // Create wall's rectangle based on location and dimensions
    var rect: Rect = Rect(x, y, x + surfaceWidth, y + surfaceHeight)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

        val sharedPreferences = context.getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)
        val wallColorId = sharedPreferences.getInt("wallColor", R.color.def)
        val wallColor = ContextCompat.getColor(context, wallColorId)
        // Set wall color
        paint.color = wallColor
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }
}