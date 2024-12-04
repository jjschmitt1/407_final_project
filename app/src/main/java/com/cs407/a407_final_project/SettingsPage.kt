package com.cs407.a407_final_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.return_to_home).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // preview text fields
        val marblePreview = findViewById<TextView>(R.id.curr_marble_color)
        val wallPreview = findViewById<TextView>(R.id.curr_wall_color)

        val sharedPreferences = getSharedPreferences("GamePreferences", Context.MODE_PRIVATE)

        // set initial marble preview
        val marblePreviewID = sharedPreferences.getInt("marbleColor", R.color.def)
        val marblePreviewColor = ContextCompat.getColor(this, marblePreviewID)

        marblePreview.setBackgroundColor(marblePreviewColor)

        // set initial wall preview
        val wallPreviewID = sharedPreferences.getInt("wallColor", R.color.def)
        val wallPreviewColor = ContextCompat.getColor(this, wallPreviewID)

        wallPreview.setBackgroundColor(wallPreviewColor)

        // implement marble color change
        // marble default
        findViewById<Button>(R.id.marble_default).setOnClickListener {
            marblePreview.setBackgroundColor(getColor(R.color.def))
            sharedPreferences.edit().putInt("marbleColor", R.color.def).apply()
        }

        // marble red
        findViewById<Button>(R.id.marble_red).setOnClickListener {
            marblePreview.setBackgroundColor(getColor(R.color.red))
            sharedPreferences.edit().putInt("marbleColor", R.color.red).apply()
        }

        // marble blue
        findViewById<Button>(R.id.marble_blue).setOnClickListener {
            marblePreview.setBackgroundColor(getColor(R.color.blue))
            sharedPreferences.edit().putInt("marbleColor", R.color.blue).apply()
        }

        // marble green
        findViewById<Button>(R.id.marble_green).setOnClickListener {
            marblePreview.setBackgroundColor(getColor(R.color.green))
            sharedPreferences.edit().putInt("marbleColor", R.color.green).apply()
        }

        // marble pink
        findViewById<Button>(R.id.marble_pink).setOnClickListener {
            marblePreview.setBackgroundColor(getColor(R.color.pink))
            sharedPreferences.edit().putInt("marbleColor", R.color.pink).apply()
        }

        // implement wall color change
        // wall default
        findViewById<Button>(R.id.wall_default).setOnClickListener {
            wallPreview.setBackgroundColor(getColor(R.color.def))
            sharedPreferences.edit().putInt("wallColor", R.color.def).apply()
        }

        // wall red
        findViewById<Button>(R.id.wall_red).setOnClickListener {
            wallPreview.setBackgroundColor(getColor(R.color.red))
            sharedPreferences.edit().putInt("wallColor", R.color.red).apply()
        }

        // wall blue
        findViewById<Button>(R.id.wall_blue).setOnClickListener {
            wallPreview.setBackgroundColor(getColor(R.color.blue))
            sharedPreferences.edit().putInt("wallColor", R.color.blue).apply()
        }

        // wall green
        findViewById<Button>(R.id.wall_green).setOnClickListener {
            wallPreview.setBackgroundColor(getColor(R.color.green))
            sharedPreferences.edit().putInt("wallColor", R.color.green).apply()
        }

        // wall pink
        findViewById<Button>(R.id.wall_pink).setOnClickListener {
            wallPreview.setBackgroundColor(getColor(R.color.pink))
            sharedPreferences.edit().putInt("wallColor", R.color.pink).apply()
        }


    }
}