package com.cs407.a407_final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LevelSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // navigate to settings
        findViewById<ImageButton>(R.id.settings_cog).setOnClickListener {
            val intent = Intent(this, SettingsPage::class.java)
            startActivity(intent)
        }

        // navigate to home screen
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // navigate to level 1
        findViewById<Button>(R.id.level1_button).setOnClickListener {
            val intent = Intent(this, Level1::class.java)
            intent.putExtra("Level", 1)
            startActivity(intent)
        }

        // navigate to level 2
        findViewById<Button>(R.id.level2_button).setOnClickListener {
            val intent = Intent(this, Level1::class.java)
            intent.putExtra("Level", 2)
            startActivity(intent)
        }

        // navigate to level 3
        findViewById<Button>(R.id.level3_button).setOnClickListener {
            val intent = Intent(this, Level1::class.java)
            intent.putExtra("Level", 3)
            startActivity(intent)
        }
    }
}