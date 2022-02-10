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
            "",
            8.4,
            "2021-12-15",
            148,
            "Released",
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            false,
            Constant.TYPE_MOVIE
        )
    }

    fun getTvShowDetail(): DetailEntity {
        return DetailEntity(
            966,
            "Hollyoaks",
            "Martine is forced to pull in a huge favour from Grace to buy Toby more time. Darren pleads his case. Social services pay Mercedes a visit. Imran does his best to impress his crush.",
            "[Soap]",
            5.2,
            "1995-10-23",
            30,
            "Returning Series",
            "/bpmLMZP3M1vLujPqHnOTnKVjRJY.jpg",
            false,
            Constant.TYPE_TV_SHOW
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
                "1995-10-23",
                "/bpmLMZP3M1vLujPqHnOTnKVjRJY.jpg",
                5.2,
                "Hollyoaks",
                966,
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
            listOf(
                GenresItem("Soap")
            ),
            966,
            "1995-10-23",
            "Martine is forced to pull in a huge favour from Grace to buy Toby more time. Darren pleads his case. Social services pay Mercedes a visit. Imran does his best to impress his crush.",
            "/bpmLMZP3M1vLujPqHnOTnKVjRJY.jpg",
            5.2,
            "Hollyoaks",
            listOf(30),
            "Returning Series"
        )
    }
}