package com.slailati.android.spectacle.ui.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.data.remote.service.TheMovieDatabaseService
import com.slailati.android.spectacle.databinding.ItemMyMovieBinding
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.ui.extension.gone
import com.slailati.android.spectacle.ui.extension.visible

class MyMoviesAdapter(
    private val onItemClickListener: OnItemClickListener<MovieModel>,
) :
    ListAdapter<MovieModel, MyMoviesAdapter.ViewHolder>(DiffCallback()) {

    companion object {
        const val ADD_MOVIE_ITEM_INDEX = 0
        const val POSTER_WIDTH = 120
        const val POSTER_HEIGHT = 174
    }

    class DiffCallback : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel,
        ): Boolean =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel,
        ): Boolean =
            oldItem.remoteId == newItem.remoteId &&
                    oldItem.title == newItem.title &&
                    oldItem.genreIds == newItem.genreIds &&
                    oldItem.posterPath == newItem.posterPath &&
                    oldItem.voteAverage == newItem.voteAverage
    }

    inner class ViewHolder(private val itemBinding: ItemMyMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(position: Int, item: MovieModel) {
            with(itemBinding) {
                if (position == ADD_MOVIE_ITEM_INDEX && !isFiltered)
                    bindAddMovieItem(item)
                else
                    bindMovieItem(item, position)
            }
        }

        private fun ItemMyMovieBinding.bindMovieItem(
            item: MovieModel,
            position: Int,
        ) {
            cvAdd.gone()
            cvVoteAverage.visible()
            clContent.setOnClickListener {}
            clContent.setOnLongClickListener {
                onItemClickListener.onLongClick(item, position)
                true
            }
            ivPosterBackground.background =
                ContextCompat.getDrawable(itemBinding.root.context,
                    R.drawable.bg_item_my_movie)
            tvVoteAverage.text = item.voteAverage.toString()
            tvTitle.text = item.title
            Glide
                .with(root)
                .load(TheMovieDatabaseService.BASE_IMAGE_POSTER_URL + item.posterPath)
                .error(R.drawable.ic_not_found_album)
                .centerCrop()
                .apply(RequestOptions().override(POSTER_WIDTH, POSTER_HEIGHT))
                .into(ivPoster)
        }

        private fun ItemMyMovieBinding.bindAddMovieItem(item: MovieModel) {
            cvAdd.visible()
            cvVoteAverage.gone()
            ivPoster.setImageDrawable(null)
            ivPosterBackground.background =
                ContextCompat.getDrawable(itemBinding.root.context,
                    R.color.white_transparent)
            tvTitle.text = itemBinding.root.context.getString(R.string.add_title)
            clContent.setOnClickListener {
                onItemClickListener.onAddButtonClick(item)
            }
            clContent.setOnLongClickListener { false }
        }
    }

    private lateinit var originalList: List<MovieModel>
    private var isFiltered: Boolean = false

    fun filterByTitle(searchTitle: String) {
        if (!isFiltered) {
            originalList = currentList
            isFiltered = true
        }

        if (searchTitle.isEmpty()) {
            isFiltered = false
            submitList(originalList)
            return
        }

        val filteredList =
            originalList.subList(ADD_MOVIE_ITEM_INDEX + 1, originalList.size).toMutableList()
                .filter { it.title.lowercase().contains(searchTitle.lowercase()) }
        submitList(filteredList)
    }

    fun removeAt(position: Int) {
        val updatedList = currentList.toMutableList()
        try {
            updatedList.remove(currentList[position])
            submitList(updatedList)
        } catch (e: IndexOutOfBoundsException) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMyMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(position, currentItem)
    }

    override fun getItemCount(): Int = currentList.size

}