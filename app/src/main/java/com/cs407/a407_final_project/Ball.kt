package com.cs407.a407_final_project

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat

const val BALL_RADIUS = 40

class Ball(private val surfaceWidth: Int, private val surfaceHeight: Int, context: Context) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var center = Point(BALL_RADIUS, BALL_RADIUS)

    val bottom
        get() = center.y + BALL_RADIUS

    init {

        val sharedPreferences = context.getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)
        val marbleColorId = sharedPreferences.getInt("marbleColor", R.color.def)
        val marbleColor = ContextCompat.getColor(context, marbleColorId)

        // set color
        paint.color = marbleColor

    }

    fun setCenter(x: Int, y: Int) {

        // Move circle center
        center.x = x
        center.y = y
    }

    fun move(velocity: PointF) {

        // Move ball's center by velocity
        center.offset(-velocity.x.toInt(), velocity.y.toInt())

        // Don't go too far down or up
        if (center.y > surfaceHeight - BALL_RADIUS) {
            center.y = surfaceHeight - BALL_RADIUS
        } else if (center.y < BALL_RADIUS) {
            center.y = BALL_RADIUS
        }

        // Don't go too far right or left
        if (center.x > surfaceWidth - BALL_RADIUS) {
            center.x = surfaceWidth - BALL_RADIUS
        } else if (center.x < BALL_RADIUS) {
            center.x = BALL_RADIUS
        }
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(center.x.toFloat(), center.y.toFloat(), BALL_RADIUS.toFloat(), paint)
    }

    fun intersects(wall: Wall): Boolean {

        // Find point on wall that is closest to ball center
        val nearestX = Math.max(wall.rect.left, Math.min(center.x, wall.rect.right))
        val nearestY = Math.max(wall.rect.top, Math.min(center.y, wall.rect.bottom))

        // Measure distance from nearest point to ball center
        val deltaX = center.x - nearestX
        val deltaY = center.y - nearestY

        // Return true if distance from ball center to nearest point is less than ball radius
        return deltaX * deltaX + deltaY * deltaY < BALL_RADIUS * BALL_RADIUS
    }

    fun intersects(goal: Goal): Boolean {

        // Find point on wall that is closest to ball center
        val nearestX = Math.max(goal.rect.left, Math.min(center.x, goal.rect.right))
        val nearestY = Math.max(goal.rect.top, Math.min(center.y, goal.rect.bottom))

        // Measure distance from nearest point to ball center
        val deltaX = center.x - nearestX
        val deltaY = center.y - nearestY

        // Return true if distance from ball center to nearest point is less than ball radius
        return deltaX * deltaX + deltaY * deltaY < BALL_RADIUS * BALL_RADIUS
    }

    fun intersects(spike: Spike): Boolean {
        val bounds = RectF()
        spike.spike.computeBounds(bounds, true)

        // Find point on wall that is closest to ball center
        val nearestX = Math.max(bounds.left, Math.min(center.x.toFloat(), bounds.right))
        val nearestY = Math.max(bounds.top, Math.min(center.y.toFloat(), bounds.bottom))

        // Measure distance from nearest point to ball center
        val deltaX = center.x - nearestX
        val deltaY = center.y - nearestY

        // Return true if distance from ball center to nearest point is less than ball radius
        return deltaX * deltaX + deltaY * deltaY < BALL_RADIUS * BALL_RADIUS
    }
}