package com.atharianr.moviecataloguebajp1.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat.IntentBuilder
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.atharianr.moviecataloguebajp1.R
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.databinding.ActivityDetailBinding
import com.atharianr.moviecataloguebajp1.utils.Constant
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener,
    DetailActivityCallback {

    lateinit var binding: ActivityDetailBinding
    private lateinit var movieTvEntity: MovieTvEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MovieCatalogueBAJP1)
        setContentView(binding.root)

        val window = this.window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val appBarLayout = binding.appBar
        appBarLayout.addOnOffsetChangedListener(this)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]
        val id = intent.getStringExtra(Constant.ID)

        if (id != null) {
            viewModel.setSelectedCourse(id)
            movieTvEntity = viewModel.getData()
        }

        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnShare.setOnClickListener {
                onShareClick(movieTvEntity)
            }

            val titleText = if (movieTvEntity.type == Constant.TYPE_MOVIE) {
                "<b><font color=#FFFFFFFF>${movieTvEntity.title}</font></b> <font color=#FFC4C4C4>(${
                    dateToYear(
                        movieTvEntity.releaseDate
                    )
                })</font>"
            } else {
                "<b><font color=#FFFFFFFF>${movieTvEntity.title}</font></b> <font color=#FFC4C4C4>(${movieTvEntity.releaseDate})</font>"
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                customTitle.text = Html.fromHtml(titleText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                tvTitle.text = Html.fromHtml(titleText, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
            tvLength.text = movieTvEntity.length
            tvGenre.text = movieTvEntity.genre
            ivCover.setImageResource(movieTvEntity.poster)

            included.apply {
                val ratingText =
                    "<font color=#9D9CD0>${getString(R.string.rating)}</font> <b><font color=#3E3CA2>${movieTvEntity.rating}</font></b>"
                val releaseDateText =
                    "<font color=#9D9CD0>${getString(R.string.release_date)}</font> <b><font color=#3E3CA2>${movieTvEntity.releaseDate}</font></b>"

                tvScore.text = getString(R.string.score_format, movieTvEntity.score)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvRating.text = Html.fromHtml(ratingText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    tvDate.text = Html.fromHtml(releaseDateText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                }
                tvDescription.text = movieTvEntity.description
                ivPoster.setImageResource(movieTvEntity.poster)
            }
        }


        setupToolbar()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        binding.apply {
            when {
                abs(verticalOffset) == appBarLayout?.totalScrollRange -> {
                    customTitle.visibility = View.VISIBLE
                    tvTitle.visibility = View.INVISIBLE
                    tvLength.visibility = View.INVISIBLE
                    tvGenre.visibility = View.INVISIBLE
                }
                verticalOffset == 0 -> {
                    customTitle.visibility = View.INVISIBLE
                    tvTitle.visibility = View.VISIBLE
                    tvLength.visibility = View.VISIBLE
                    tvGenre.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupToolbar() {
        var state = 0
        binding.customTitle.setOnClickListener {
            if (state == 0) {
                state = 1
                binding.appBar.setExpanded(true)
            } else {
                state = 0
                binding.appBar.setExpanded(false)
            }
        }
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

    override fun onShareClick(data: MovieTvEntity) {
        val mimeType = "text/plain"

        if (data.type == Constant.TYPE_MOVIE) {
            IntentBuilder(this@DetailActivity)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_this_movie))
                .setText(getString(R.string.check_out, data.title))
                .startChooser()
        } else {
            IntentBuilder(this@DetailActivity)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_this_tv_show))
                .setText(getString(R.string.check_out, data.title))
                .startChooser()
        }

    }
}