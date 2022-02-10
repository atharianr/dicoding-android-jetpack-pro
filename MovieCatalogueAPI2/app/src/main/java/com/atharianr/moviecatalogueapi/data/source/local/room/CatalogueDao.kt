package com.atharianr.moviecatalogueapi.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM tv_show_entities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: DetailEntity)

    @Query("SELECT * FROM detail_entities WHERE id = :id")
    fun getDetailById(id: Int): LiveData<DetailEntity>

    @Update
    fun updateDetail(detail: DetailEntity)

    @Query("SELECT * FROM detail_entities WHERE favorite = 1 AND type = 0")
    fun getFavoriteMovies(): DataSource.Factory<Int, DetailEntity>

    @Query("SELECT * FROM detail_entities WHERE favorite = 1 AND type = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, DetailEntity>
}