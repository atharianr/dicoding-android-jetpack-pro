package com.atharianr.moviecatalogueapi.ui.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.atharianr.moviecatalogueapi.R
import com.atharianr.moviecatalogueapi.data.source.local.entity.DetailEntity
import com.atharianr.moviecatalogueapi.databinding.ActivityDetailBinding
import com.atharianr.moviecatalogueapi.utils.Constant
import com.atharianr.moviecatalogueapi.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


class DetailActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener,
    DetailActivityCallback {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MovieCatalogueAPI)
        setContentView(binding.root)

        val window = this.window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val appBarLayout = binding.appBar
        appBarLayout.addOnOffsetChangedListener(this)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val id = intent.getStringExtra(Constant.ID)
        val type = intent.getIntExtra(Constant.TYPE, 0)

        if (id != null) {
            viewModel.setType(id, type)
            viewModel.getMovieDetail().observe(this, { detailEntity ->

                val titleText =
                    "<b><font color=#FFFFFFFF>${detailEntity.title}</font></b> <font color=#FFC4C4C4>(${
                        dateToYear(detailEntity.releaseDate)
                    })</font>"
                val statusText =
                    "<font color=#9D9CD0>${getString(R.string.status)}</font> <b><font color=#3E3CA2>${detailEntity.status}</font></b>"
                val releaseDateText =
                    "<font color=#9D9CD0>${getString(R.string.release_date)}</font> <b><font color=#3E3CA2>${detailEntity.releaseDate}</font></b>"

                val genre = detailEntity.genre.toString().replace("[", "").replace("]", "")
                val hours = detailEntity.runtime / 60
                val minutes = detailEntity.runtime % 60

                binding.apply {
                    btnBack.setOnClickListener {
                        onBackPressed()
                    }

                    btnShare.setOnClickListener {
                        onShareClick(detailEntity, type)
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        customTitle.text =
                            Html.fromHtml(titleText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        tvTitle.text = Html.fromHtml(titleText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    }

                    when {
                        detailEntity.runtime < 60 -> {
                            tvLength.text =
                                getString(R.string.length_format_m, detailEntity.runtime)
                        }
                        (detailEntity.runtime % 60) == 0 -> {
                            tvLength.text = getString(R.string.length_format_h, hours)
                        }
                        else -> {
                            tvLength.text = getString(R.string.length_format_h_m, hours, minutes)
                        }
                    }

                    tvGenre.text = genre
                    Glide.with(this@DetailActivity)
                        .load(Constant.IMAGE_BASE_URL + detailEntity.backdrop)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivCover)

                    included.apply {
                        tvScore.text =
                            getString(R.string.score_format, (detailEntity.score * 10).toInt())
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            tvStatus.text =
                                Html.fromHtml(statusText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                            tvDate.text =
                                Html.fromHtml(releaseDateText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        }
                        tvDescription.text = detailEntity.overview
                        Glide.with(this@DetailActivity)
                            .load(Constant.IMAGE_BASE_URL + detailEntity.poster)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivPoster)
                    }
                }
            })
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val year = dateFormat.parse(date)

        if (year != null) {
            return yearFormat.format(year)
        }

        return null
    }

    private fun isLoading(state: Boolean) {
        binding.included.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
                tvScore.visibility = View.GONE
                tvStatus.visibility = View.GONE
                tvDate.visibility = View.GONE
                tvDescriptionTitle.visibility = View.GONE
                tvDescription.visibility = View.GONE

            } else {
                progressBar.visibility = View.GONE
                tvScore.visibility = View.VISIBLE
                tvStatus.visibility = View.VISIBLE
                tvDate.visibility = View.VISIBLE
                tvDescriptionTitle.visibility = View.VISIBLE
                tvDescription.visibility = View.VISIBLE
            }
        }
    }

    override fun onShareClick(data: DetailEntity, type: Int) {
        val mimeType = "text/plain"

        when (type) {
            Constant.TYPE_MOVIE -> {
                ShareCompat.IntentBuilder(this@DetailActivity)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_this_movie))
                    .setText(getString(R.string.check_out, data.title))
                    .startChooser()
            }

            Constant.TYPE_TV_SHOW -> {
                ShareCompat.IntentBuilder(this@DetailActivity)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_this_tv_show))
                    .setText(getString(R.string.check_out, data.title))
                    .startChooser()
            }
        }
    }
}