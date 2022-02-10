package com.atharianr.moviecatalogueapi.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.atharianr.moviecatalogueapi.data.source.CatalogueRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getMoviePopular() {
        val dummyMovies = Resource.success(moviePagedList)
        `when`(dummyMovies.data?.size).thenReturn(4)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(catalogueRepository.getMoviePopular()).thenReturn(movies)
        val movie = viewModel.getMoviePopular().value?.data
        verify(catalogueRepository).getMoviePopular()
        Assert.assertNotNull(movie)
        Assert.assertEquals(4, movie?.size)

        viewModel.getMoviePopular().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}