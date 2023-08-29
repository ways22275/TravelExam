package com.example.travalexam.di

import com.example.travalexam.network.TravelService
import com.example.travalexam.repository.TravelRepository
import com.example.travalexam.ui.HomeViewModel
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
}

val modules = listOf(network, services, database, repos, viewModels)