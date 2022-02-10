package com.atharianr.moviecataloguebajp1.data

data class MovieTvEntity(
    var id: String,
    var title: String,
    var description: String,
    var genre: String,
    var score: Int,
    var releaseDate: String,
    var length: String,
    var rating: String,
    var poster: Int,
    var type: Int
)
