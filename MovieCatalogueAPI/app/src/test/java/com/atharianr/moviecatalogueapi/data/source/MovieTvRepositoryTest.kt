package com.atharianr.moviecatalogueapi.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atharianr.moviecatalogueapi.data.source.remote.RemoteDataSource
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.atharianr.moviecatalogueapi.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieTvRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieTvRepository = FakeMovieTvRepository(remote)

    private val moviesResponse = DataDummy.getRemoteMovie()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.getRemoteMovieDetail()

    private val tvShowResponse = DataDummy.getRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.getRemoteTvShowDetail()

    @Test
    fun getMoviePopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onMoviesLoaded(
                moviesResponse
            )
            null
        }.`when`(remote).getMoviePopular(any())

        val movies = LiveDataTestUtil.getValue(movieTvRepository.getMoviePopular())
        verify(remote).getMoviePopular(any())
        assertNotNull(movies)
        assertEquals(moviesResponse.size, movies.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailLoaded(
                movieDetail
            )
            null
        }.`when`(remote).getMovieDetail(any(), eq(movieId))

        val movieDetailEntity =
            LiveDataTestUtil.getValue(movieTvRepository.getMovieDetail(movieId))
        verify(remote).getMovieDetail(any(), eq(movieId))
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.id)
    }

    @Test
    fun getTvShowPopular() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onTvShowsLoaded(
                tvShowResponse
            )
            null
        }.`when`(remote).getTvShowPopular(any())

        val tvShows = LiveDataTestUtil.getValue(movieTvRepository.getTvShowPopular())
        verify(remote).getTvShowPopular(any())
        assertNotNull(tvShows)
        assertEquals(tvShowResponse.size, tvShows.size)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailLoaded(
                tvShowDetail
            )
            null
        }.`when`(remote).getTvShowDetail(any(), eq(tvShowId))

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(movieTvRepository.getTvShowDetail(tvShowId))
        verify(remote).getTvShowDetail(any(), eq(tvShowId))
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.id)
    }
}