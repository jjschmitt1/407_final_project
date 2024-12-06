package com.cs407.a407_final_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Handler
import android.os.Looper


class MarbleMazeGame(private val context: Context, private val surfaceWidth: Int, private val surfaceHeight: Int, private val levelID: Int) {

    private val ball = Ball(surfaceWidth, surfaceHeight, context)
    private val walls = mutableListOf<Wall>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var goal = Goal(0, 2016, 100, 100)
    private var gameOver = false
    private val spikes = mutableListOf<Spike>()

    init {
        paint.textSize = 90f
        paint.color = Color.RED

        // canvas width = 1080
        // canvas height = 2116
        if (levelID == 1) {
            walls.add(Wall(0, 500, 600, 60, context))
            walls.add(Wall(480, 1000, 600, 60, context))
            walls.add(Wall(0, 1500, 600, 60, context))
        } else if (levelID == 2){
            walls.add(Wall(0, 500, 600, 60, context))
            walls.add(Wall(480, 1000, 600, 60, context))
            walls.add(Wall(0, 1500, 600, 60, context))
            spikes.add(Spike(740f, 560f, 100, 0))
            spikes.add(Spike(240f, 1060f, 100, 0))
            spikes.add(Spike(740f, 1560f, 100, 0))
        } else if (levelID == 3) {
            walls.add(Wall(0, 500, 600, 60, context))
            walls.add(Wall(480, 1000, 600, 60, context))
            walls.add(Wall(0, 1500, 600, 60, context))
            walls.add(Wall(790, 0, 60, 460, context))
            walls.add(Wall(190, 500, 60, 460, context))
            walls.add(Wall(790, 1000, 60, 460, context))
            spikes.add(Spike(740f, 560f, 100, 0))
            spikes.add(Spike(240f, 1060f, 100, 0))
            spikes.add(Spike(740f, 1560f, 100, 0))
        }else if (levelID == 4) {
            walls.add(Wall(0, 500, 750, 60, context))
            walls.add(Wall(340, 1500, 750, 60, context))
            walls.add(Wall(690, 500, 60,500, context))
            walls.add(Wall(340, 1000, 60, 500, context))
            goal = Goal(0, 2016, 100, 100)
        }else if (levelID == 5) {
            walls.add(Wall(0, 500, 750, 60, context))
            walls.add(Wall(340, 1500, 750, 60, context))
            walls.add(Wall(690, 500, 60,500, context))
            walls.add(Wall(340, 1000, 60, 500, context))
            goal = Goal(0, 2016, 100, 100)
            spikes.add(Spike(1080f, 800f, 150, 270))
            spikes.add(Spike(690f, 1500f, 150, 0))
            spikes.add(Spike(690f, 1000f, 150, 270))
            spikes.add(Spike(0f, 1250f, 150, 90))

        }else if (levelID == 6){
            walls.add(Wall(0, 400, 850, 60, context))
            walls.add(Wall(790, 400, 60, 1300, context))
            walls.add(Wall(200, 1700, 650, 60, context))
            walls.add(Wall(200, 800, 60, 900, context))
            walls.add(Wall(200, 800, 450, 60, context))
            walls.add(Wall(590, 800, 60, 700, context))
            walls.add(Wall(430, 1440, 160, 60, context))
            walls.add(Wall(430, 950, 60, 500, context))
            goal = Goal(490, 1350, 100, 100)
        }else if (levelID == 7){
            walls.add(Wall(0, 400, 850, 60, context))
            walls.add(Wall(790, 400, 60, 1300, context))
            walls.add(Wall(200, 1700, 650, 60, context))
            walls.add(Wall(200, 800, 60, 900, context))
            walls.add(Wall(200, 800, 450, 60, context))
            walls.add(Wall(590, 800, 60, 700, context))
            walls.add(Wall(430, 1440, 160, 60, context))
            walls.add(Wall(430, 950, 60, 500, context))
            goal = Goal(490, 1350, 100, 100)
            spikes.add(Spike(820f, 2116f, 250,0))
            spikes.add(Spike(0f, 1866f, 180,90))
            spikes.add(Spike(200f, 460f, 180, 180))
            spikes.add(Spike(790f, 680f, 200,270))
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
            //Update unlockedLevel
            LevelSelect.saveProgress(this.context, levelID+1)
            val text = "You won!"
            val textBounds = Rect()
            paint.getTextBounds(text, 0, text.length, textBounds)
            canvas.drawText(
                text, surfaceWidth / 2f - textBounds.exactCenterX(),
                surfaceHeight / 2f - textBounds.exactCenterY(), paint
            )

            // automatically switch to next level (may not be worth it due to performance)
            if (levelID < 7) {
                val intent = Intent(context, Level1::class.java)
                intent.putExtra("Level", levelID + 1)
                (context as Activity).finish()
                Handler(Looper.getMainLooper()).postDelayed({
                    context.startActivity(intent)
                }, 100)
            }
        }
    }

}