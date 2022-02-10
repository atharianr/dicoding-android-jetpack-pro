package com.atharianr.moviecatalogueapi.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.atharianr.moviecatalogueapi.data.source.CatalogueRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShowPopular(): LiveData<Resource<PagedList<TvShowEntity>>> =
        catalogueRepository.getTvShowPopular()
}