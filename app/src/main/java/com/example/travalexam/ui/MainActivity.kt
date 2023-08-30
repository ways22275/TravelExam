package com.example.travalexam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.travalexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityMainBinding.inflate(layoutInflater)

    setContentView(binding.root)

    window.statusBarColor = ContextCompat.getColor(window.context, android.R.color.transparent)

    WindowCompat.getInsetsController(window, binding.root).isAppearanceLightStatusBars = true

  }
}