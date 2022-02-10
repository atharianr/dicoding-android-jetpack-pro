package com.atharianr.moviecatalogueapi.di

import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieTvRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieTvRepository.getInstance(remoteDataSource)
    }
}