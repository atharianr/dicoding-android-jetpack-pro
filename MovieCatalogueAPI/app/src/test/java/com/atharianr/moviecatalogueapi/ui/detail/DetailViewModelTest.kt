package com.atharianr.moviecatalogueapi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.atharianr.moviecatalogueapi.data.source.MovieTvRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.utils.Constant
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieTvRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = DataDummy.getMovieDetail()
        val dummyId = dummyMovie.id.toString()
        val data = MutableLiveData<DetailEntity>()
        data.value = dummyMovie

        `when`(movieTvRepository.getMovieDetail(dummyId)).thenReturn(data)
        viewModel.setType(dummyId, Constant.TYPE_MOVIE)
        val detailEntity = viewModel.getMovieDetail().value as DetailEntity
        verify(movieTvRepository).getMovieDetail(dummyId)

        assertNotNull(detailEntity)
        assertEquals(dummyMovie.id, detailEntity.id)
        assertEquals(dummyMovie.title, detailEntity.title)
        assertEquals(dummyMovie.overview, detailEntity.overview)
        assertEquals(dummyMovie.genre, detailEntity.genre)
        assertEquals(dummyMovie.score, detailEntity.score, 0.0)
        assertEquals(dummyMovie.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyMovie.runtime, detailEntity.runtime)
        assertEquals(dummyMovie.status, detailEntity.status)
        assertEquals(dummyMovie.poster, detailEntity.poster)
        assertEquals(dummyMovie.backdrop, detailEntity.backdrop)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = DataDummy.getTvShowDetail()
        val dummyId = dummyTvShow.id.toString()
        val data = MutableLiveData<DetailEntity>()
        data.value = dummyTvShow

        `when`(movieTvRepository.getMovieDetail(dummyId)).thenReturn(data)
        viewModel.setType(dummyId, Constant.TYPE_MOVIE)
        val detailEntity = viewModel.getMovieDetail().value as DetailEntity
        verify(movieTvRepository).getMovieDetail(dummyId)

        assertNotNull(detailEntity)
        assertEquals(dummyTvShow.id, detailEntity.id)
        assertEquals(dummyTvShow.title, detailEntity.title)
        assertEquals(dummyTvShow.overview, detailEntity.overview)
        assertEquals(dummyTvShow.genre, detailEntity.genre)
        assertEquals(dummyTvShow.score, detailEntity.score, 0.0)
        assertEquals(dummyTvShow.releaseDate, detailEntity.releaseDate)
        assertEquals(dummyTvShow.runtime, detailEntity.runtime)
        assertEquals(dummyTvShow.status, detailEntity.status)
        assertEquals(dummyTvShow.poster, detailEntity.poster)
        assertEquals(dummyTvShow.backdrop, detailEntity.backdrop)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}