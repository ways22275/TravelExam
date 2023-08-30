package com.example.travalexam.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travalexam.data.Attraction
import com.example.travalexam.databinding.ItemAttractionBinding
import com.example.travalexam.utils.bindImage

class HomeAttractionsAdapter(
  private val onItemClicked: ((Attraction) -> Unit)
) : PagingDataAdapter<Attraction, AttractionsViewHolder>(DIFF_CALLBACK) {

  companion object {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Attraction>() {
      override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
        return oldItem == newItem
      }
    }
  }

  override fun onBindViewHolder(holder: AttractionsViewHolder, position: Int) {
    holder.bind(getItem(position) ?: return)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionsViewHolder {
    val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return AttractionsViewHolder(binding = binding, onItemClicked = onItemClicked)
  }
}

class AttractionsViewHolder(
  private val binding: ItemAttractionBinding,
  private val onItemClicked: ((Attraction) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(item: Attraction) {
    binding.imageView.bindImage(item.images.firstOrNull()?.src)
    binding.titleTextView.text = item.name
    binding.descriptionTextView.text = item.introduction
    binding.root.setOnClickListener {
      onItemClicked.invoke(item)
    }
  }
}