package com.cs407.a407_final_project

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView(context: Context, attrs: AttributeSet) :
    SurfaceView(context, attrs), SurfaceHolder.Callback {

    private var gameThread: GameThread? = null
    private var levelID: Int = 0;

    init {
        holder.addCallback(this)
    }

    constructor(context: Context, attrs: AttributeSet, levelID: Int) : this(context, attrs) {
        this.levelID = levelID
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread = GameThread(context, holder, levelID)
        gameThread?.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Nothing to do
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        gameThread?.stopThread()
    }

    fun changeAcceleration(x: Float, y: Float) {
        gameThread?.changeAcceleration(x, y)
    }

    fun setLevelID(id: Int){
        levelID = id
    }
}
