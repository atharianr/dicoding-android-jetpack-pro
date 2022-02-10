package com.atharianr.moviecatalogueapi.ui.home.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.atharianr.moviecatalogueapi.databinding.FragmentTvShowBinding
import com.atharianr.moviecatalogueapi.utils.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowViewModel.getTvShowPopular().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    val tvShowAdapter = TvShowAdapter()
                    tvShowAdapter.setData(it)

                    binding.rvTvShow.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = tvShowAdapter
                    }
                    isLoading(false)
                }
            })
        }

        isLoading(true)
    }

    private fun isLoading(state: Boolean) {
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
                rvTvShow.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvTvShow.visibility = View.VISIBLE
            }
        }
    }
}