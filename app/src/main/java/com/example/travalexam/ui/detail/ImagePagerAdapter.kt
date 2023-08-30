package com.example.travalexam.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travalexam.data.Image
import com.example.travalexam.databinding.ItemImageBinding
import com.example.travalexam.utils.bindImage

class ImagePagerAdapter(private val onItemSelected: (Int, String) -> Unit) : RecyclerView.Adapter<ImagePagerViewHolder>() {

  private val images = mutableListOf<Image>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
    return ImagePagerViewHolder(
      ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
      onItemSelected
    )
  }

  override fun getItemCount(): Int {
    return images.count()
  }

  override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
    holder.bind(images[position], position)
  }

  fun update(images: List<Image>) {
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {
      override fun getOldListSize(): Int = this@ImagePagerAdapter.images.size

      override fun getNewListSize(): Int = images.size

      override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return this@ImagePagerAdapter.images[oldItemPosition].src == images[newItemPosition].src
      }

      override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
      }

    }).dispatchUpdatesTo(this)

    this.images.clear()
    this.images.addAll(images)
  }
}

class ImagePagerViewHolder(
  private val binding: ItemImageBinding,
  private val onItemSelected: (Int, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
  fun bind(image: Image, position: Int) {
    binding.root.setOnClickListener {
      onItemSelected.invoke(position, image.src)
    }
    with(binding.pagerItemImageView) {
      bindImage(image.src)
      strokeColor = if (image.selected) {
        ContextCompat.getColorStateList(context, android.R.color.holo_red_dark)
      } else {
        ContextCompat.getColorStateList(context, android.R.color.transparent)
      }
    }
  }
}