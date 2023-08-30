package com.example.travalexam.ui.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.travalexam.data.Language
import com.example.travalexam.data.Language.Companion.getLanguage
import com.example.travalexam.data.getDisplayName
import com.example.travalexam.data.getIndex
import com.example.travalexam.repository.TravelRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeViewModel(repo: TravelRepository) : ViewModel() {

  val attractionSource = repo.attractionsFlow.cachedIn(viewModelScope).asLiveData().distinctUntilChanged()

  private val _currentLanguage = repo.currentLanguage
  val currentLanguage: LiveData<Language> = _currentLanguage.distinctUntilChanged()

  fun showLanguageSelectDialog(view: View) {
    val checked = _currentLanguage.value?.getIndex() ?: 0
    val list = mutableListOf<CharSequence>()
    Language.values().forEach {
      list.add(it.getDisplayName())
    }
    MaterialAlertDialogBuilder(view.context)
      .setSingleChoiceItems(list.toTypedArray(), checked) { dialog, which ->
        _currentLanguage.value = getLanguage(which)
        dialog.dismiss()
      }
      .create()
      .show()
  }
}