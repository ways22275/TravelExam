package com.example.travalexam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.travalexam.data.Language
import com.example.travalexam.repository.TravelRepository

class HomeViewModel(repo: TravelRepository) : ViewModel() {

  val attractionSource = repo.attractionsFlow.cachedIn(viewModelScope).asLiveData().distinctUntilChanged()

  private val _currentLanguage = repo.currentLanguage
  val currentLanguage: LiveData<Language> = _currentLanguage

  fun updateCurrentLanguage(language: Language) {
    _currentLanguage.value = language
  }
}