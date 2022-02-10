package com.atharianr.moviecatalogueapi.utils

import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.data.source.remote.response.*

object DataDummy {
    fun getMoviePopular(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                634649,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Spider-Man: No Way Home",
                "2021-12-15",
                8.4,
                Constant.TYPE_MOVIE
            ),
            MovieEntity(
                568124,
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "Encanto",
                "2021-11-24",
                7.8,
                Constant.TYPE_MOVIE
            ),
            MovieEntity(
                460458,
                "/6WR7wLCX0PGLhj51qyvK8MIxtT5.jpg",
                "Resident Evil: Welcome to Raccoon City",
                "2021-11-24",
                6.0,
                Constant.TYPE_MOVIE
            )
        )
    }

    fun getTvShowPopular(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                77169,
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                "Cobra Kai",
                "2018-05-02",
                8.1,
                Constant.TYPE_TV_SHOW
            ),
            TvShowEntity(
                115036,
                "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
                "The Book of Boba Fett",
                "2021-12-29",
                8.4,
                Constant.TYPE_TV_SHOW
            ),
            TvShowEntity(
                71914,
                "/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg",
                "The Wheel of Time",
                "2021-11-18",
                8.0,
                Constant.TYPE_TV_SHOW
            )
        )
    }

    fun getMovieDetail(): DetailEntity {
        return DetailEntity(
            634649,
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            listOf("Fantasy", "Action", "Adventure"),
            8.4,
            "2021-12-15",
            148,
            "Released",
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg"
        )
    }

    fun getTvShowDetail(): DetailEntity {
        return DetailEntity(
            85552,
            "Euphoria",
            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            listOf("Drama"),
            8.4,
            "2019-06-16",
            148,
            "Returning Series",
            "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg"
        )
    }

    fun getRemoteMovie(): List<Movie> {
        return listOf(
            Movie(
                "Spider-Man: No Way Home",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "2021-12-15",
                8.4,
                634649
            ),
            Movie(
                "Encanto",
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "2021-11-24",
                7.8,
                568124
            ),
            Movie(
                "Resident Evil: Welcome to Raccoon City",
                "/6WR7wLCX0PGLhj51qyvK8MIxtT5.jpg",
                "2021-11-24",
                6.0,
                460458
            )
        )
    }

    fun getRemoteMovieDetail(): MovieDetailResponse {
        return MovieDetailResponse(
            "Spider-Man: No Way Home",
            "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            listOf(
                GenresItem("Action"),
                GenresItem("Adventure"),
                GenresItem("Science Fiction"),
            ),
            634649,
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            148,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "2021-12-15",
            8.4,
            "Released"
        )
    }

    fun getRemoteTvShow(): List<TvShow> {
        return listOf(
            TvShow(
                "2018-05-02",
                "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
                8.1,
                "Cobra Kai",
                77169,
            ),
            TvShow(
                "2021-12-29",
                "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
                8.4,
                "The Book of Boba Fett",
                115036
            ),
            TvShow(
                "2021-11-18",
                "/mpgDeLhl8HbhI03XLB7iKO6M6JE.jpg",
                8.0,
                "The Wheel of Time",
                71914
            )
        )
    }

    fun getRemoteTvShowDetail(): TvShowDetailResponse {
        return TvShowDetailResponse(
            "/35SS0nlBhu28cSe7TiO3ZiywZhl.jpg",
            listOf(
                GenresItem("Action & Adventure"),
                GenresItem("Drama"),
            ),
            77169,
            "2018-05-02",
            "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
            "/6POBWybSBDBKjSs1VAQcnQC1qyt.jpg",
            8.1,
            "Cobra Kai",
            listOf(30),
            "Returning Series"
        )
    }
}