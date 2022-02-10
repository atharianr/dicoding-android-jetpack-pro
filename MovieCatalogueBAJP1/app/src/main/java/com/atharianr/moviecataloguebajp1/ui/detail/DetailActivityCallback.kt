package com.atharianr.moviecataloguebajp1.ui.detail

import com.atharianr.moviecataloguebajp1.data.MovieTvEntity

interface DetailActivityCallback {
    fun onShareClick(data: MovieTvEntity)
}