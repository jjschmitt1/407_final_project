package com.cs407.a407_final_project

import android.content.Context
import android.view.SurfaceHolder
import android.graphics.PointF
import android.util.Log

class GameThread(context: Context, private val surfaceHolder: SurfaceHolder, private val levelID: Int) : Thread() {

    private var marbleMazeGame: MarbleMazeGame
    private var threadRunning = false
    private val velocity = PointF()

    init {
        threadRunning = true

        // Create a ball with boundaries determined by SurfaceView
        val canvas = surfaceHolder.lockCanvas()
        Log.d("width", canvas.width.toString())
        Log.d("height", canvas.height.toString())
        marbleMazeGame = MarbleMazeGame(context, canvas.width, canvas.height, levelID)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    override fun run() {
        while (threadRunning) {
            val canvas = surfaceHolder.lockCanvas()
            canvas?.let {
                marbleMazeGame.update(velocity)
                marbleMazeGame.draw(canvas)
                surfaceHolder.unlockCanvasAndPost(it)
            }
        }
    }

    fun changeAcceleration(xForce: Float, yForce: Float) {
        velocity.x = xForce
        velocity.y = yForce
    }

    fun stopThread() {
        threadRunning = false
    }
}