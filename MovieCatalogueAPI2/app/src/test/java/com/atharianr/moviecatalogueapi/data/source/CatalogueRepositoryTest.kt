package com.atharianr.moviecatalogueapi.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.atharianr.moviecatalogueapi.data.LiveDataTestUtil
import com.atharianr.moviecatalogueapi.data.PagedListUtil
import com.atharianr.moviecatalogueapi.data.source.local.LocalDataSource
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.MovieEntity
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.data.source.remote.RemoteDataSource
import com.atharianr.moviecatalogueapi.utils.AppExecutors
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.atharianr.moviecatalogueapi.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummy.getRemoteMovie()
    private val movieId = moviesResponse[0].id.toString()
    private val movieDetail = DataDummy.getMovieDetail()

    private val tvShowResponse = DataDummy.getRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.getTvShowDetail()

    @Test
    fun getMoviePopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getMoviePopular()

        val movies = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMoviePopular()))
        verify(local).getMovies()
        assertNotNull(movies.data)
        assertEquals(moviesResponse.size.toLong(), movies.data?.size?.toLong())
    }

    @Test
    fun getTvShowPopular() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShowPopular()

        val tvShows = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShowPopular()))
        verify(local).getTvShows()
        assertNotNull(tvShows.data)
        assertEquals(tvShowResponse.size.toLong(), tvShows.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<DetailEntity>()
        dummyMovie.value = movieDetail
        `when`(local.getDetail(movieDetail.id)).thenReturn(dummyMovie)

        val movieDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getMovieDetail(movieId))
        verify(local).getDetail(movieDetail.id)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<DetailEntity>()
        dummyTvShow.value = tvShowDetail
        `when`(local.getDetail(tvShowDetail.id)).thenReturn(dummyTvShow)

        val tvShowDetailEntity =
            LiveDataTestUtil.getValue(catalogueRepository.getTvShowDetail(tvShowId))
        verify(local).getDetail(tvShowDetail.id)
        assertNotNull(tvShowDetailEntity)
        assertEquals(tvShowDetail.id, tvShowDetailEntity.data?.id)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getMoviePopular()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(DataDummy.getRemoteMovie().size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteTvShows()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShowPopular()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(DataDummy.getRemoteTvShow().size.toLong(), tvShowEntities.data?.size?.toLong())
    }
}