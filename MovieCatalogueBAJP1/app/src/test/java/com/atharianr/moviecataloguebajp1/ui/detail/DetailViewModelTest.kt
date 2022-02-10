package com.atharianr.moviecataloguebajp1.ui.detail

import com.atharianr.moviecataloguebajp1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyData()[0]
    private val id = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedCourse(id)
    }

    @Test
    fun getData() {
        viewModel.setSelectedCourse(dummyMovie.id)
        val data = viewModel.getData()
        assertNotNull(data)
        assertEquals(dummyMovie.id, data.id)
        assertEquals(dummyMovie.title, data.title)
        assertEquals(dummyMovie.description, data.description)
        assertEquals(dummyMovie.genre, data.genre)
        assertEquals(dummyMovie.score, data.score)
        assertEquals(dummyMovie.releaseDate, data.releaseDate)
        assertEquals(dummyMovie.length, data.length)
        assertEquals(dummyMovie.rating, data.rating)
        assertEquals(dummyMovie.poster, data.poster)
        assertEquals(dummyMovie.type, data.type)
    }
}