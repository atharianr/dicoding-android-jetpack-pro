package com.atharianr.moviecataloguebajp1.ui.home.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(14, tvShows.size)
    }
}