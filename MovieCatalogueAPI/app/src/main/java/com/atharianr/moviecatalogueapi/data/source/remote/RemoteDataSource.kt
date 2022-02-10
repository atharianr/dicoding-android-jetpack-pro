package com.atharianr.moviecatalogueapi.data.source.remote

import android.util.Log
import com.atharianr.moviecatalogueapi.data.source.remote.network.ApiConfig
import com.atharianr.moviecatalogueapi.data.source.remote.response.*
import com.atharianr.moviecatalogueapi.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getMoviePopular(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getMoviePopular()
            .enqueue(object : Callback<MovieResponse<Movie>> {
                override fun onResponse(
                    call: Call<MovieResponse<Movie>>,
                    response: Response<MovieResponse<Movie>>
                ) {
                    if (response.isSuccessful) {
                        callback.onMoviesLoaded(response.body()?.results)
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("RemoteDataSource", "onFailure: ${response.message()}")
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<MovieResponse<Movie>>, t: Throwable) {
                    Log.e("RemoteDataSource", "onFailure: ${t.message.toString()}")
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getMovieDetail(callback: LoadMovieDetailCallback, movieId: String) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getMovieDetail(movieId)
            .enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onMovieDetailLoaded(response.body())
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("RemoteDataSource", "onFailure: ${response.message()}")
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Log.e("RemoteDataSource", "onFailure: ${t.message.toString()}")
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getTvShowPopular(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTvShowPopular()
            .enqueue(object : Callback<TvShowResponse<TvShow>> {
                override fun onResponse(
                    call: Call<TvShowResponse<TvShow>>,
                    response: Response<TvShowResponse<TvShow>>
                ) {
                    if (response.isSuccessful) {
                        callback.onTvShowsLoaded(response.body()?.results)
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("RemoteDataSource", "onFailure: ${response.message()}")
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<TvShowResponse<TvShow>>, t: Throwable) {
                    Log.e("RemoteDataSource", "onFailure: ${t.message.toString()}")
                    EspressoIdlingResource.decrement()
                }
            })
    }

    fun getTvShowDetail(callback: LoadTvShowDetailCallback, tvShowId: String) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getTvShowDetail(tvShowId)
            .enqueue(object : Callback<TvShowDetailResponse> {
                override fun onResponse(
                    call: Call<TvShowDetailResponse>,
                    response: Response<TvShowDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onTvShowDetailLoaded(response.body())
                        EspressoIdlingResource.decrement()
                    } else {
                        Log.e("RemoteDataSource", "onFailure: ${response.message()}")
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                    Log.e("RemoteDataSource", "onFailure: ${t.message.toString()}")
                    EspressoIdlingResource.decrement()
                }
            })
    }

    interface LoadMoviesCallback {
        fun onMoviesLoaded(movies: List<Movie>?)
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailLoaded(movie: MovieDetailResponse?)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsLoaded(tvShows: List<TvShow>?)
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailLoaded(tvShow: TvShowDetailResponse?)
    }
}