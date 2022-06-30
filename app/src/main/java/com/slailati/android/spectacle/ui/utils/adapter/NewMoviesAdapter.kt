package com.slailati.android.spectacle.ui.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.ItemNewMovieBinding
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService.Companion.BASE_IMAGE_POSTER_URL

class NewMoviesAdapter(
    private val onItemClickListener: OnItemClickListener<MovieModel>,
) :
    ListAdapter<MovieModel, NewMoviesAdapter.ViewHolder>(DiffCallback()) {
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

    inner class ViewHolder(private val itemBinding: ItemNewMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: MovieModel) {
            with(itemBinding) {
                tvVoteAverage.text = item.voteAverage.toString()
                tvTitle.text = item.title
                Glide
                    .with(root)
                    .load(BASE_IMAGE_POSTER_URL + item.posterPath)
                    .error(R.drawable.ic_not_found_album)
                    .centerCrop()
                    .apply(RequestOptions().override(180, 261))
                    .into(ivPoster)
                clContent.setOnClickListener {
                    onItemClickListener.onAddButtonClick(item)
                }
            }
        }
    }

    private lateinit var beforeFilterList: List<MovieModel>
    private var isAltered: Boolean = false

    fun filterByTitle(searchTitle: String) {
        if (!isAltered) {
            beforeFilterList = currentList
            isAltered = true

            val filteredList = currentList.toMutableList()
                .filter { it.title.lowercase().contains(searchTitle.lowercase()) }
            submitList(filteredList)
        } else {
            if (searchTitle.isEmpty())
                submitList(beforeFilterList)
            else {
                val filteredList = currentList.toMutableList()
                    .filter { it.title.lowercase().contains(searchTitle.lowercase()) }
                submitList(filteredList)
            }
        }
    }

    fun addNewPage(newPageMovies: List<MovieModel>) {
        val updatedList = currentList.toMutableList()
        updatedList.addAll(newPageMovies)
        submitList(updatedList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemNewMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currentList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = currentList.size

}