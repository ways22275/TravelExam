package com.example.travalexam.di

import androidx.lifecycle.SavedStateHandle
import com.example.travalexam.network.TravelService
import com.example.travalexam.repository.TravelRepository
import com.example.travalexam.ui.detail.DetailViewModel
import com.example.travalexam.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val network = module {
  single {
    provideRetrofit()
  }
}

val services = module {
  single {
    provideService<TravelService>(get())
  }
}

val database = module {
  single {
    provideDatabase(get())
  }
}

val repos = module {
  factory {
    TravelRepository(get(), get())
  }
}

val viewModels = module {
  factory {
    HomeViewModel(get())
  }
  viewModel { (handle: SavedStateHandle) -> DetailViewModel(handle, get()) }
}

val modules = listOf(network, services, database, repos, viewModels)