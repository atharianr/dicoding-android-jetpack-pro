package com.atharianr.moviecatalogueapi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.di.Injection
import com.atharianr.moviecatalogueapi.ui.detail.DetailViewModel
import com.atharianr.moviecatalogueapi.ui.home.movie.MovieViewModel
import com.atharianr.moviecatalogueapi.ui.home.tvshow.TvShowViewModel

class ViewModelFactory constructor(private val MovieTvRepository: MovieTvRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(MovieTvRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(MovieTvRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(MovieTvRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}