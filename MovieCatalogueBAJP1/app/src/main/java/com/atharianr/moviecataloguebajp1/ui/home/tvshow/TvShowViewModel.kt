package com.atharianr.moviecataloguebajp1.ui.home.tvshow

import androidx.lifecycle.ViewModel
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.utils.Constant
import com.atharianr.moviecataloguebajp1.utils.DataDummy

class TvShowViewModel : ViewModel() {

    private var listTvShow = arrayListOf<MovieTvEntity>()

    fun getTvShows(): List<MovieTvEntity> {
        val movies = DataDummy.generateDummyData()
        for (i in movies.indices) {
            val tvShow = movies[i]
            if (tvShow.type == Constant.TYPE_TV_SHOW) {
                listTvShow.add(tvShow)
            }
        }

        return listTvShow
    }
}