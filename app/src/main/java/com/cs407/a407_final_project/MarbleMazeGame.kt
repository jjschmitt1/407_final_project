package com.cs407.a407_final_project

import android.graphics.*

class MarbleMazeGame(private val surfaceWidth: Int, private val surfaceHeight: Int, private val levelID: Int) {

    private val ball = Ball(surfaceWidth, surfaceHeight)
    private val walls = mutableListOf<Wall>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val goal = Goal(0, 2016, 100, 100)
    private var gameOver = false
    private val spikes = mutableListOf<Spike>()

    init {
        paint.textSize = 90f
        paint.color = Color.RED

        // canvas width = 1080
        // canvas height = 2116
        if (levelID == 1) {
            walls.add(Wall(0, 500, 600, 60))
            walls.add(Wall(480, 1000, 600, 60))
            walls.add(Wall(0, 1500, 600, 60))
        } else if (levelID == 2){
            walls.add(Wall(480, 500, 600, 60))
            walls.add(Wall(480, 1500, 600, 60))
            walls.add(Wall(0, 1000, 600, 60))
        } else if (levelID == 3) {
            // just to show how spikes work, change actual level later
            spikes.add(Spike(200f, 200f, 100, 0))
            spikes.add(Spike(200f, 400f, 100, 90))
            spikes.add(Spike(200f, 600f, 100, 180))
            spikes.add(Spike(200f, 800f, 100, 270))
        }

        newGame()
    }

    fun newGame() {
        gameOver = false

        // Reset ball at the top of the screen
        ball.setCenter(surfaceWidth / 2, BALL_RADIUS + 10)
    }

    fun update(velocity: PointF) {
        if (gameOver) return

        // Move ball and walls
        ball.move(velocity)

        // Check for collision
        for (wall in walls) {
            if (ball.intersects(wall)) {
                ball.move(PointF(-velocity.x, -velocity.y))
            }
        }

        for (spike in spikes) {
            if (ball.intersects(spike)) {
                ball.setCenter(surfaceWidth / 2, BALL_RADIUS + 10)
            }
        }

        // Check for win
        if (ball.intersects(goal)) {
            gameOver = true
        }
    }

    fun draw(canvas: Canvas) {

        // Wipe canvas clean
        canvas.drawColor(Color.WHITE)

        // Draw ball and walls
        ball.draw(canvas)
        for (wall in walls) {
            wall.draw(canvas)
        }
        goal.draw(canvas)
        for (spike in spikes) {
            spike.draw(canvas)
        }

        if (gameOver) {
            val text = "You won!"
            val textBounds = Rect()
            paint.getTextBounds(text, 0, text.length, textBounds)
            canvas.drawText(
                text, surfaceWidth / 2f - textBounds.exactCenterX(),
                surfaceHeight / 2f - textBounds.exactCenterY(), paint
            )
        }
    }
}