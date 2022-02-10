package com.atharianr.moviecatalogueapi.ui.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.atharianr.moviecatalogueapi.R
import com.atharianr.moviecatalogueapi.data.source.local.entity.TvShowEntity
import com.atharianr.moviecatalogueapi.databinding.ItemsListMovieTvBinding
import com.atharianr.moviecatalogueapi.ui.detail.DetailActivity
import com.atharianr.moviecatalogueapi.utils.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.*

class TvShowAdapter : PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsListMovieTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class ViewHolder(private val binding: ItemsListMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShowEntity) {
            binding.apply {
                val scoreInt = (data.voteAverage * 10).toInt()

                Glide.with(itemView)
                    .load(Constant.IMAGE_BASE_URL + data.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivMovie)
                tvTitle.text = data.title
                tvGenre.text = dateToYear(data.releaseDate)
                tvScore.text =
                    itemView.context.getString(R.string.score_format, scoreInt)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(Constant.ID, data.id.toString())
                intent.putExtra(Constant.TYPE, data.type)
                itemView.context.startActivity(intent)
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
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
