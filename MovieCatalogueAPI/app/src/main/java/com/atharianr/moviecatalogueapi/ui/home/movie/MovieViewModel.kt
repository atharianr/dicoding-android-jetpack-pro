package com.atharianr.moviecatalogueapi.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity

class MovieViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getMoviePopular(): LiveData<List<MovieEntity>> = movieTvRepository.getMoviePopular()
}