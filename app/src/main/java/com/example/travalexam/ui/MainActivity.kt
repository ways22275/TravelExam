package com.example.travalexam.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travalexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(ActivityMainBinding.inflate(layoutInflater).root)
  }
}