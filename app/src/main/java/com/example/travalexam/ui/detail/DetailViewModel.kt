package com.example.travalexam.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import com.example.travalexam.data.Image
import com.example.travalexam.repository.TravelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest

const val KEY_ATTRACTION_ID = "attractionId"
class DetailViewModel(
  savedStateHandle: SavedStateHandle,
  private val repository: TravelRepository
) : ViewModel() {

  @OptIn(ExperimentalCoroutinesApi::class)
  val attractionFlow = savedStateHandle.getLiveData<Int>(KEY_ATTRACTION_ID).asFlow().flatMapLatest {
    repository.getAttractionFlow(it)
  }

  private val _images: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()
  val images: LiveData<List<Image>> = _images

  fun updateImages(images: List<Image>) {
    val updated = images.mapIndexed { index, image ->
      if (index == 0) {
        image.selected = true
      }
      image
    }
    _images.value = updated
  }

  fun syncImages(position: Int) {
    val origin = _images.value ?: return
    val updated = origin.mapIndexed { index, image ->
      image.selected = index == position
      image
    }
    _images.value = updated
  }
}