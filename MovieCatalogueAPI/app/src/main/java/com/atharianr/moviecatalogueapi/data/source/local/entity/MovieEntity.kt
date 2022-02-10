package com.atharianr.moviecatalogueapi.data.source.local.entity

import com.atharianr.moviecatalogueapi.utils.Constant

data class MovieEntity(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val type: Int = Constant.TYPE_MOVIE
)