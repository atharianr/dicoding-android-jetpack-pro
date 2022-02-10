package com.atharianr.moviecatalogueapi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.utils.Constant

class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {

    private lateinit var detailData: LiveData<DetailEntity>

    fun setType(id: String, type: Int) {
        when (type) {
            Constant.TYPE_MOVIE -> {
                detailData = movieTvRepository.getMovieDetail(id)
            }

            Constant.TYPE_TV_SHOW -> {
                detailData = movieTvRepository.getTvShowDetail(id)
            }
        }
    }

    fun getMovieDetail(): LiveData<DetailEntity> = detailData
}