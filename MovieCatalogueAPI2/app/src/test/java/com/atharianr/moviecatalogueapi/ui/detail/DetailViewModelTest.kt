package com.atharianr.moviecatalogueapi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.atharianr.moviecatalogueapi.data.source.CatalogueRepository
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.utils.Constant
import com.atharianr.moviecatalogueapi.utils.DataDummy
import com.atharianr.moviecatalogueapi.vo.Resource
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
import org.mockito.kotlin.verifyNoMoreInteractions

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(DataDummy.getMovieDetail())
        val dummyId = dummyMovie.data?.id.toString()
        val data = MutableLiveData<Resource<DetailEntity>>()
        data.value = dummyMovie

        `when`(catalogueRepository.getMovieDetail(dummyId)).thenReturn(data)
        viewModel.setType(dummyId, Constant.TYPE_MOVIE)
        val detailEntity = viewModel.getMovieDetail().value as Resource<DetailEntity>
        verify(catalogueRepository).getMovieDetail(dummyId)

        assertNotNull(detailEntity)
        assertEquals(dummyMovie.data?.id, detailEntity.data?.id)
        assertEquals(dummyMovie.data?.title, detailEntity.data?.title)
        assertEquals(dummyMovie.data?.overview, detailEntity.data?.overview)
        assertEquals(dummyMovie.data?.genre, detailEntity.data?.genre)
        dummyMovie.data?.score?.let {
            detailEntity.data?.score?.let { it1 ->
                assertEquals(
                    it,
                    it1,
                    0.0
                )
            }
        }
        assertEquals(dummyMovie.data?.releaseDate, detailEntity.data?.releaseDate)
        assertEquals(dummyMovie.data?.runtime, detailEntity.data?.runtime)
        assertEquals(dummyMovie.data?.status, detailEntity.data?.status)
        assertEquals(dummyMovie.data?.poster, detailEntity.data?.poster)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = Resource.success(DataDummy.getTvShowDetail())
        val dummyId = dummyTvShow.data?.id.toString()
        val data = MutableLiveData<Resource<DetailEntity>>()
        data.value = dummyTvShow

        `when`(catalogueRepository.getMovieDetail(dummyId)).thenReturn(data)
        viewModel.setType(dummyId, Constant.TYPE_MOVIE)
        val detailEntity = viewModel.getMovieDetail().value as Resource<DetailEntity>
        verify(catalogueRepository).getMovieDetail(dummyId)

        assertNotNull(detailEntity)
        assertEquals(dummyTvShow.data?.id, detailEntity.data?.id)
        assertEquals(dummyTvShow.data?.title, detailEntity.data?.title)
        assertEquals(dummyTvShow.data?.overview, detailEntity.data?.overview)
        assertEquals(dummyTvShow.data?.genre, detailEntity.data?.genre)
        dummyTvShow.data?.score?.let {
            detailEntity.data?.score?.let { it1 ->
                assertEquals(
                    it,
                    it1,
                    0.0
                )
            }
        }
        assertEquals(dummyTvShow.data?.releaseDate, detailEntity.data?.releaseDate)
        assertEquals(dummyTvShow.data?.runtime, detailEntity.data?.runtime)
        assertEquals(dummyTvShow.data?.status, detailEntity.data?.status)
        assertEquals(dummyTvShow.data?.poster, detailEntity.data?.poster)

        viewModel.getMovieDetail().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetail = Resource.success(DataDummy.getMovieDetail())
        val movie = MutableLiveData<Resource<DetailEntity>>()
        movie.value = dummyDetail

        `when`(catalogueRepository.getMovieDetail(dummyDetail.data?.id.toString())).thenReturn(movie)
        viewModel.setType(dummyDetail.data?.id.toString(), Constant.TYPE_MOVIE)
        val detailData = viewModel.getMovieDetail().value
        verify(catalogueRepository).getMovieDetail(dummyDetail.data?.id.toString())

        detailData?.data?.let { viewModel.setFavorite(it) }
        detailData?.data?.let { verify(catalogueRepository).setFavorite(it, true) }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetail = Resource.success(DataDummy.getTvShowDetail())
        val tvShow = MutableLiveData<Resource<DetailEntity>>()
        tvShow.value = dummyDetail

        `when`(catalogueRepository.getTvShowDetail(dummyDetail.data?.id.toString())).thenReturn(
            tvShow
        )
        viewModel.setType(dummyDetail.data?.id.toString(), Constant.TYPE_TV_SHOW)
        val detailData = viewModel.getMovieDetail().value
        verify(catalogueRepository).getTvShowDetail(dummyDetail.data?.id.toString())

        detailData?.data?.let { viewModel.setFavorite(it) }
        detailData?.data?.let { verify(catalogueRepository).setFavorite(it, true) }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun deleteFavoriteMovie() {
        val dummyDetail = DataDummy.getMovieDetail()
        val movie = MutableLiveData<DetailEntity>()
        movie.value = dummyDetail.copy(favorite = true)

        val dummyDetailResource = Resource.success(movie.value)
        val movieResource = MutableLiveData<Resource<DetailEntity>>()
        movieResource.value = dummyDetailResource

        `when`(catalogueRepository.getMovieDetail(dummyDetailResource.data?.id.toString())).thenReturn(
            movieResource
        )
        viewModel.setType(dummyDetailResource.data?.id.toString(), Constant.TYPE_MOVIE)
        val detailData = viewModel.getMovieDetail().value
        verify(catalogueRepository).getMovieDetail(dummyDetailResource.data?.id.toString())

        detailData?.data?.let { viewModel.setFavorite(it) }
        detailData?.data?.let { verify(catalogueRepository).setFavorite(it, false) }
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun deleteFavoriteTvShow() {
        val dummyDetail = DataDummy.getTvShowDetail()
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyDetail.copy(favorite = true)

        val dummyDetailResource = Resource.success(tvShow.value)
        val tvShowResource = MutableLiveData<Resource<DetailEntity>>()
        tvShowResource.value = dummyDetailResource

        `when`(catalogueRepository.getTvShowDetail(dummyDetailResource.data?.id.toString())).thenReturn(
            tvShowResource
        )
        viewModel.setType(dummyDetailResource.data?.id.toString(), Constant.TYPE_TV_SHOW)
        val detailData = viewModel.getMovieDetail().value
        verify(catalogueRepository).getTvShowDetail(dummyDetailResource.data?.id.toString())

        detailData?.data?.let { viewModel.setFavorite(it) }
        detailData?.data?.let { verify(catalogueRepository).setFavorite(it, false) }
        verifyNoMoreInteractions(observer)
    }
}