package com.atharianr.moviecatalogueapi.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    fun getTvShowPopular(): LiveData<List<TvShowEntity>> = movieTvRepository.getTvShowPopular()
}