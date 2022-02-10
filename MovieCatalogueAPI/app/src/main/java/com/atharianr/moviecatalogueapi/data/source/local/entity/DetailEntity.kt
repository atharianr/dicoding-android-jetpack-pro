package com.atharianr.moviecatalogueapi.data.source.local.entity

data class DetailEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val genre: List<String>,
    val score: Double,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val poster: String,
    val backdrop: String
)
