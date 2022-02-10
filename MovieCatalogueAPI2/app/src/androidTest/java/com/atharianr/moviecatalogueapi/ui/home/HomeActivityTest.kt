package com.atharianr.moviecatalogueapi.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.atharianr.moviecatalogueapi.R
import com.atharianr.moviecatalogueapi.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.view_pager2)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_cover)).check(matches((isDisplayed())))
        onView(withId(R.id.iv_poster)).check(matches((isDisplayed())))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.view_pager2)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_cover)).check(matches((isDisplayed())))
        onView(withId(R.id.iv_poster)).check(matches((isDisplayed())))
    }

    @Test
    fun setLoadDeleteFavoriteMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.btn_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_menu)).perform(click())
        onView(withText("Favorite Movies")).perform(click())
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
    }

    @Test
    fun setLoadDeleteFavoriteTvShows() {
        onView(withId(R.id.view_pager2)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.btn_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_menu)).perform(click())
        onView(withText("Favorite TV Shows")).perform(click())
        onView(withId(R.id.rv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.btn_back)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_back)).perform(click())
    }
}