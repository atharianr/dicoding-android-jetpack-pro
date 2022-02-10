package com.atharianr.moviecatalogueapi.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieTvRepository)
    }

    @Test
    fun getMoviePopular() {
        val dummyMovies = DataDummy.getMoviePopular()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        Mockito.`when`(movieTvRepository.getMoviePopular()).thenReturn(movies)
        val movie = viewModel.getMoviePopular().value
        verify(movieTvRepository).getMoviePopular()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        viewModel.getMoviePopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}