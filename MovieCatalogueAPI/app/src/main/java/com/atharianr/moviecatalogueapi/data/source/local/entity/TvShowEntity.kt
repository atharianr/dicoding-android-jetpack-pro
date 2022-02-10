package com.atharianr.moviecatalogueapi.data.source.local.entity

import com.atharianr.moviecatalogueapi.utils.Constant

data class TvShowEntity(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val type: Int = Constant.TYPE_TV_SHOW
)