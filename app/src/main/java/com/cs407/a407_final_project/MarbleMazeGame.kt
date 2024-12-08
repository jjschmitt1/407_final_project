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
            walls.add(Wall(0, 500, 750, 60, context))
            walls.add(Wall(340, 1500, 750, 60, context))
            walls.add(Wall(690, 500, 60,500, context))
            walls.add(Wall(340, 1000, 60, 500, context))
            goal = Goal(0, 2016, 100, 100)
        } else if (levelID == 3) {
            walls.add(Wall(0, 500, 600, 60, context))
            walls.add(Wall(480, 1000, 600, 60, context))
            walls.add(Wall(0, 1500, 600, 60, context))
            spikes.add(Spike(740f, 560f, 100, 0))
            spikes.add(Spike(240f, 1060f, 100, 0))
            spikes.add(Spike(740f, 1560f, 100, 0))
        }else if (levelID == 4) {
            walls.add(Wall(0, 500, 600, 60, context))
            walls.add(Wall(480, 1000, 600, 60, context))
            walls.add(Wall(0, 1500, 600, 60, context))
            walls.add(Wall(790, 0, 60, 460, context))
            walls.add(Wall(190, 500, 60, 460, context))
            walls.add(Wall(790, 1000, 60, 460, context))
            spikes.add(Spike(740f, 560f, 100, 0))
            spikes.add(Spike(240f, 1060f, 100, 0))
            spikes.add(Spike(740f, 1560f, 100, 0))
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
        }else if (levelID == 8) {
            // RHS
            spikes.add(Spike(750f, 300f, 150, 270))
            spikes.add(Spike(825f, 500f, 150, 270))
            spikes.add(Spike(900f, 700f, 150, 270))
            spikes.add(Spike(975f, 900f, 150, 270))
            spikes.add(Spike(1050f, 1100f, 150, 270))
            spikes.add(Spike(975f, 1300f, 150, 270))
            spikes.add(Spike(900f, 1500f, 150, 270))
            spikes.add(Spike(825f, 1700f, 150, 270))
            spikes.add(Spike(750f, 1900f, 150, 270))
            // LHS
            spikes.add(Spike(250f, 200f, 150, 90))
            spikes.add(Spike(325f, 400f, 150, 90))
            spikes.add(Spike(400f, 600f, 150, 90))
            spikes.add(Spike(475f, 800f, 150, 90))

            spikes.add(Spike(550f, 1000f, 150, 90))

            spikes.add(Spike(475f, 1200f, 150, 90))
            spikes.add(Spike(400f, 1400f, 150, 90))
            spikes.add(Spike(325f, 1600f, 150, 90))
            spikes.add(Spike(250f, 1800f, 150, 90))

            goal = Goal(440, 2016, 100, 100)

        } else if (levelID == 9){
            walls.add(Wall(440, 0, 40, 300, context))
            walls.add(Wall(620, 0, 40, 500, context))
            walls.add(Wall(200, 500, 460, 40, context))
            walls.add(Wall(200, 250, 40, 250, context))
            spikes.add(Spike(260f, 50f, 80, 180))

            walls.add(Wall(0, 800, 700, 40, context))
            spikes.add(Spike(720f, 820f, 180, 0))

            walls.add(Wall(200, 1200, 880, 40, context))
            spikes.add(Spike(800f, 1280f, 120, 0))
            spikes.add(Spike(550f, 1280f, 120, 0))
            spikes.add(Spike(300f, 1280f, 120, 0))

            walls.add(Wall(0, 1600, 880, 40, context))
            spikes.add(Spike(700f, 1680f, 120, 0))
            spikes.add(Spike(450f, 1680f, 120, 0))
            spikes.add(Spike(200f, 1680f, 120, 0))

            spikes.add(Spike(0f, 1820f, 100, 90))
            spikes.add(Spike(180f, 2040f, 100, 0))
        } else if (levelID == 10){
            spikes.add(Spike(900f, 880f, 120, 0))
            spikes.add(Spike(500f, 880f, 120, 0))
            spikes.add(Spike(100f, 880f, 120, 0))

            spikes.add(Spike(700f, 480f, 120, 0))
            spikes.add(Spike(300f, 480f, 120, 0))

            spikes.add(Spike(900f, 1680f, 120, 0))
            spikes.add(Spike(500f, 1680f, 120, 0))
            spikes.add(Spike(100f, 1680f, 120, 0))

            spikes.add(Spike(700f, 1280f, 120, 0))
            spikes.add(Spike(300f, 1280f, 120, 0))

            goal = Goal(490, 2016, 100, 100)
        } else if (levelID == 11){
            walls.add(Wall(200, 200, 880, 40, context))
            spikes.add(Spike(0f, 600f, 180, 0))
            spikes.add(Spike(400f, 240f, 180, 180))
            spikes.add(Spike(400f, 600f, 180, 0))
            spikes.add(Spike(800f, 240f, 180, 180))


            walls.add(Wall(0, 600, 880, 40, context))
            spikes.add(Spike(500f, 1000f, 180, 0))
            spikes.add(Spike(480f, 640f, 180, 180))
            spikes.add(Spike(900f, 1000f, 180, 0))
            spikes.add(Spike(880f, 640f, 180, 180))

            walls.add(Wall(200, 1000, 880, 40, context))
            spikes.add(Spike(0f, 1400f, 180, 0))
            spikes.add(Spike(400f, 1040f, 180, 180))
            spikes.add(Spike(400f, 1400f, 180, 0))
            spikes.add(Spike(800f, 1040f, 180, 180))

            walls.add(Wall(0, 1400, 880, 40, context))
            spikes.add(Spike(500f, 1800f, 180, 0))
            spikes.add(Spike(480f, 1440f, 180, 180))
            spikes.add(Spike(900f, 1800f, 180, 0))
            spikes.add(Spike(880f, 1440f, 180, 180))

            walls.add(Wall(200, 1800, 880, 40, context))
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