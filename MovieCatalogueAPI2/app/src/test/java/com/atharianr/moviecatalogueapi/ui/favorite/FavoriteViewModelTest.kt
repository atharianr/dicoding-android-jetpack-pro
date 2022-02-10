package com.atharianr.moviecatalogueapi.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.atharianr.moviecatalogueapi.data.source.CatalogueRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.utils.Constant
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<DetailEntity>>

    @Mock
    private lateinit var pagedList: PagedList<DetailEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(4)
        val movie = MutableLiveData<PagedList<DetailEntity>>()
        movie.value = dummyMovies

        `when`(catalogueRepository.getFavoriteMovies()).thenReturn(movie)
        viewModel.setType(Constant.TYPE_MOVIE)
        val movieEntity = viewModel.getFavorite().value
        verify(catalogueRepository).getFavoriteMovies()

        assertNotNull(movieEntity)
        assertEquals(4, movieEntity?.size)

        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(4)
        val tvShow = MutableLiveData<PagedList<DetailEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogueRepository.getFavoriteTvShows()).thenReturn(tvShow)
        viewModel.setType(Constant.TYPE_TV_SHOW)
        val tvShowEntity = viewModel.getFavorite().value
        verify(catalogueRepository).getFavoriteTvShows()

        assertNotNull(tvShowEntity)
        assertEquals(4, tvShowEntity?.size)

        viewModel.getFavorite().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}