package com.example.travalexam.utils

import android.graphics.Color
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

object Utils {
  fun fullscreenBehindStatusBar(window: Window, view: View) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, view).show(WindowInsetsCompat.Type.systemBars())
    window.statusBarColor = Color.TRANSPARENT
  }
}