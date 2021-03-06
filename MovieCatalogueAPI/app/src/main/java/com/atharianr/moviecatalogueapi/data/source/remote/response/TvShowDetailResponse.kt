package com.atharianr.moviecatalogueapi.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @field:SerializedName("status")
    val status: String
)