package com.atharianr.moviecatalogueapi.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.atharianr.moviecatalogueapi.data.source.CatalogueRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.vo.Resource

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMoviePopular(): LiveData<Resource<PagedList<MovieEntity>>> =
        catalogueRepository.getMoviePopular()
}