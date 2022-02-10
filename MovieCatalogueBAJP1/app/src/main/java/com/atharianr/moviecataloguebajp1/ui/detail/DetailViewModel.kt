package com.atharianr.moviecataloguebajp1.ui.detail

import androidx.lifecycle.ViewModel
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var id: String

    fun setSelectedCourse(id: String) {
        this.id = id
    }

    fun getData(): MovieTvEntity {
        lateinit var movies: MovieTvEntity
        val movieEntities = DataDummy.generateDummyData()

        for (entity in movieEntities) {
            if (entity.id == id) {
                movies = entity
            }
        }

        return movies
    }
}