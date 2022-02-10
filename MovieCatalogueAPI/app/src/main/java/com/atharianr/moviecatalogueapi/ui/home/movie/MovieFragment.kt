package com.atharianr.moviecatalogueapi.ui.home.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.atharianr.moviecatalogueapi.databinding.FragmentMovieBinding
import com.atharianr.moviecatalogueapi.utils.ViewModelFactory


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieViewModel.getMoviePopular().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    val movieAdapter = MovieAdapter()
                    movieAdapter.setData(it)

                    binding.rvMovie.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = movieAdapter
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
                rvMovie.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                rvMovie.visibility = View.VISIBLE
            }
        }
    }
}