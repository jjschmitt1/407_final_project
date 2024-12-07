package com.cs407.a407_final_project

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.get_started).setOnClickListener {
            val intent = Intent(this, LevelSelect::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.how_to_play).setOnClickListener { 
            val intent = Intent(this, HowToPlay::class.java)
            startActivity(intent)
        }



    }
}