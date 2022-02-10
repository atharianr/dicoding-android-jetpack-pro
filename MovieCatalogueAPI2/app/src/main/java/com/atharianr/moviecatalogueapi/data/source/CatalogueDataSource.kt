package com.atharianr.moviecatalogueapi.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.vo.Resource

interface CatalogueDataSource {
    fun getMoviePopular(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId: String): LiveData<Resource<DetailEntity>>
    fun getTvShowPopular(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowDetail(tvShowId: String): LiveData<Resource<DetailEntity>>

    fun setFavorite(detailEntity: DetailEntity, state: Boolean)
    fun getFavoriteMovies(): LiveData<PagedList<DetailEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<DetailEntity>>
}