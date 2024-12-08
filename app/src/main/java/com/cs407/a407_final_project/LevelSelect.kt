package com.cs407.a407_final_project

import android.content.Context
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

        // Load unlocked level from SharedPreferences
        val sharedPreferences = getSharedPreferences("GameProgress", Context.MODE_PRIVATE)
        val unlockedLevel = sharedPreferences.getInt("UnlockedLevel", 1) // Default to level 1

        // Enable buttons based on unlocked levels
        for (level in 1..10) {
            val buttonId = resources.getIdentifier("level${level}_button", "id", packageName)
            val button = findViewById<Button>(buttonId)
            if (level <= unlockedLevel) {
                button.isEnabled = true
                button.setOnClickListener {
                    val intent = Intent(this, Level1::class.java)
                    intent.putExtra("Level", level)
                    startActivity(intent)
                }
            } else {
                button.isEnabled = false
            }
        }

        // Navigate to settings
        findViewById<ImageButton>(R.id.settings_cog).setOnClickListener {
            val intent = Intent(this, SettingsPage::class.java)
            startActivity(intent)
        }

        // Navigate to home screen
        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        // Method to save progress (call this when a level is completed)
        fun saveProgress(context: Context, level: Int) {
            val sharedPreferences = context.getSharedPreferences("GameProgress", Context.MODE_PRIVATE)
            val unlockedLevel = sharedPreferences.getInt("UnlockedLevel", 1)
            if (level > unlockedLevel) {
                sharedPreferences.edit().putInt("UnlockedLevel", level).apply()
            }
        }
    }
}
