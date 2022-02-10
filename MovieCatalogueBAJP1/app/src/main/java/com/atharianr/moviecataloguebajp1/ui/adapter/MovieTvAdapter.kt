package com.atharianr.moviecataloguebajp1.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atharianr.moviecataloguebajp1.R
import com.atharianr.moviecataloguebajp1.data.MovieTvEntity
import com.atharianr.moviecataloguebajp1.databinding.ItemsListMovieTvBinding
import com.atharianr.moviecataloguebajp1.ui.detail.DetailActivity
import com.atharianr.moviecataloguebajp1.utils.Constant

class MovieTvAdapter : RecyclerView.Adapter<MovieTvAdapter.ViewHolder>() {

    private var listData = ArrayList<MovieTvEntity>()

    fun setMovies(data: List<MovieTvEntity>) {
        this.listData.clear()
        this.listData.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsListMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(private val binding: ItemsListMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieTvEntity) {
            binding.apply {
                ivMovie.setImageResource(data.poster)
                tvTitle.text = data.title
                tvGenre.text = data.genre
                tvLength.text = data.length
                tvScore.text = itemView.context.getString(R.string.score_format, data.score)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(Constant.ID, data.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}
