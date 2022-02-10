package com.atharianr.moviecataloguebajp1.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.atharianr.moviecataloguebajp1.R
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.utils.Constant
import com.atharianr.moviecataloguebajp1.utils.DataDummy
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class HomeActivityTest {

    private val dummyData = DataDummy.generateDummyData()
    private var listMovie = arrayListOf<MovieTvEntity>()
    private var listTvShow = arrayListOf<MovieTvEntity>()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        for (i in dummyData.indices) {
            val data = dummyData[i]
            if (data.type == Constant.TYPE_MOVIE) {
                listMovie.add(data)
            } else {
                listTvShow.add(data)
            }
        }
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyData.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyData.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(
            matches(
                withText(
                    "${listMovie[0].title} (${
                        dateToYear(
                            listMovie[0].releaseDate
                        )
                    })"
                )
            )
        )
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_length)).check(matches(withText(listMovie[0].length)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(listMovie[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText("${listMovie[0].score}/100")))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText("Rating: ${listMovie[0].rating}")))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(withText("Release date: ${listMovie[0].releaseDate}")))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(listMovie[0].description)))
        onView(withId(R.id.iv_cover)).check(matches((isDisplayed())))
        onView(withId(R.id.iv_poster)).check(matches((isDisplayed())))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText("${listTvShow[0].title} (${listTvShow[0].releaseDate})")))
        onView(withId(R.id.tv_length)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_length)).check(matches(withText(listTvShow[0].length)))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(listTvShow[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText("${listTvShow[0].score}/100")))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText("Rating: ${listTvShow[0].rating}")))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(withText("Release date: ${listTvShow[0].releaseDate}")))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(listTvShow[0].description)))
        onView(withId(R.id.iv_cover)).check(matches((isDisplayed())))
        onView(withId(R.id.iv_poster)).check(matches((isDisplayed())))
    }

    private fun dateToYear(date: String): String? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val year = dateFormat.parse(date)

        if (year != null) {
            return yearFormat.format(year)
        }

        return null
    }
}