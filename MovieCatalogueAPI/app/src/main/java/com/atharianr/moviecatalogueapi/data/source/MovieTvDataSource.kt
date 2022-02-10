package com.atharianr.moviecatalogueapi.data.source

import androidx.lifecycle.LiveData
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity

interface MovieTvDataSource {
    fun getMoviePopular(): LiveData<List<MovieEntity>>
    fun getMovieDetail(movieId: String): LiveData<DetailEntity>
    fun getTvShowPopular(): LiveData<List<TvShowEntity>>
    fun getTvShowDetail(tvShowId: String): LiveData<DetailEntity>
}