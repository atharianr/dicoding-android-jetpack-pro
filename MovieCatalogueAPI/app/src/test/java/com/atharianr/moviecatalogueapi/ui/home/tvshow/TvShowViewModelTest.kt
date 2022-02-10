package com.atharianr.moviecatalogueapi.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieTvRepository)
    }

    @Test
    fun getTvShowPopular() {
        val dummyTvShows = DataDummy.getTvShowPopular()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        Mockito.`when`(movieTvRepository.getTvShowPopular()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShowPopular().value
        verify(movieTvRepository).getTvShowPopular()
        assertNotNull(tvShow)
        assertEquals(3, tvShow?.size)

        viewModel.getTvShowPopular().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}