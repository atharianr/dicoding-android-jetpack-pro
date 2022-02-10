package com.atharianr.moviecatalogueapi.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.data.source.remote.RemoteDataSource
import com.atharianr.moviecatalogueapi.data.source.remote.response.Movie
import com.atharianr.moviecatalogueapi.data.source.remote.response.MovieDetailResponse
import com.atharianr.moviecatalogueapi.data.source.remote.response.TvShow
import com.atharianr.moviecatalogueapi.data.source.remote.response.TvShowDetailResponse

class MovieTvRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieTvDataSource {

    companion object {
        @Volatile
        private var instance: MovieTvRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieTvRepository =
            instance ?: synchronized(this) {
                instance ?: MovieTvRepository(remoteData).apply { instance = this }
            }
    }

    override fun getMoviePopular(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMoviePopular(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>?) {
                val movieList = ArrayList<MovieEntity>()

                if (movies != null) {
                    for (response in movies) {
                        with(response) {
                            val movie = MovieEntity(id, posterPath, title, releaseDate, voteAverage)
                            movieList.add(movie)
                        }
                    }
                    movieResults.postValue(movieList)
                }
            }
        })

        return movieResults
    }

    override fun getMovieDetail(movieId: String): LiveData<DetailEntity> {
        val movieDetailResults = MutableLiveData<DetailEntity>()

        remoteDataSource.getMovieDetail(object : RemoteDataSource.LoadMovieDetailCallback {
            override fun onMovieDetailLoaded(movie: MovieDetailResponse?) {
                if (movie != null) {
                    with(movie) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                            id,
                            title,
                            overview,
                            listGenres,
                            voteAverage,
                            releaseDate,
                            runtime,
                            status,
                            posterPath,
                            backdropPath
                        )

                        movieDetailResults.postValue(detailMovie)
                    }
                }
            }
        }, movieId)

        return movieDetailResults
    }

    override fun getTvShowPopular(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShowPopular(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsLoaded(tvShows: List<TvShow>?) {
                val tvShowList = ArrayList<TvShowEntity>()

                if (tvShows != null) {
                    for (response in tvShows) {
                        with(response) {
                            val tvShow =
                                TvShowEntity(id, posterPath, name, firstAirDate, voteAverage)
                            tvShowList.add(tvShow)
                        }
                    }
                    tvShowResults.postValue(tvShowList)
                }
            }
        })

        return tvShowResults
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<DetailEntity> {
        val tvShowDetailResults = MutableLiveData<DetailEntity>()

        remoteDataSource.getTvShowDetail(object : RemoteDataSource.LoadTvShowDetailCallback {
            override fun onTvShowDetailLoaded(tvShow: TvShowDetailResponse?) {
                if (tvShow != null) {
                    with(tvShow) {
                        val listGenres = ArrayList<String>()

                        for (genre in genres) {
                            listGenres.add(genre.name)
                        }

                        val detailMovie = DetailEntity(
                            id,
                            name,
                            overview,
                            listGenres,
                            voteAverage,
                            firstAirDate,
                            episodeRunTime[0],
                            status,
                            posterPath,
                            backdropPath
                        )

                        tvShowDetailResults.postValue(detailMovie)
                    }
                }
            }
        }, tvShowId)

        return tvShowDetailResults
    }
}