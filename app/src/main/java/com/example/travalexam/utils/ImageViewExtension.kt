package com.example.travalexam.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.bindImage(imageUrl: String?) {
  Glide.with(this)
    .load(imageUrl)
    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
    .error(android.R.drawable.progress_indeterminate_horizontal)
    .into(this)
}