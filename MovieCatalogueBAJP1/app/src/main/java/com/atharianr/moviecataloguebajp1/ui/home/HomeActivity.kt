package com.atharianr.moviecataloguebajp1.ui.home

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.atharianr.moviecataloguebajp1.R
import com.atharianr.moviecataloguebajp1.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MovieCatalogueBAJP1)
        setContentView(binding.root)

        val window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.app_purple)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(supportFragmentManager, this.lifecycle)

        binding.viewPager2.adapter = sectionsPagerAdapter
        TabLayoutMediator(
            binding.tabs,
            binding.viewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}