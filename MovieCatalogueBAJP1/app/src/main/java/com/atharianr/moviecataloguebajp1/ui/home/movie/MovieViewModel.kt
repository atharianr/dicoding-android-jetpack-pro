package com.atharianr.moviecataloguebajp1.ui.home.movie

import androidx.lifecycle.ViewModel
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.utils.Constant
import com.atharianr.moviecataloguebajp1.utils.DataDummy

class MovieViewModel : ViewModel() {

    private var listMovie = arrayListOf<MovieTvEntity>()

    fun getMovies(): List<MovieTvEntity> {
        val movies = DataDummy.generateDummyData()
        for (i in movies.indices) {
            val movie = movies[i]
            if (movie.type == Constant.TYPE_MOVIE) {
                listMovie.add(movie)
            }
        }

        return listMovie
    }
}