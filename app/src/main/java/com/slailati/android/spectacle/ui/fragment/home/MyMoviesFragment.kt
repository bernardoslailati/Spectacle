package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.slailati.android.spectacle.databinding.FragmentMyMoviesBinding
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService.Companion.ACTION_ID
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService.Companion.ANIMATION_ID
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService.Companion.DRAMA_ID
import com.slailati.android.spectacle.domain.service.TheMovieDatabaseService.Companion.SCIENCE_FICTION_ID
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.extension.isNetworkAvailable
import com.slailati.android.spectacle.ui.fragment.BaseFragment
import com.slailati.android.spectacle.ui.utils.adapter.MyMoviesAdapter
import com.slailati.android.spectacle.ui.utils.adapter.OnItemClickListener
import com.slailati.android.spectacle.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyMoviesFragment : BaseFragment() {

    private val movieViewModel: MovieViewModel by sharedViewModel()

    private var _binding: FragmentMyMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            clContent.setOnClickListener {
                etSearchMyMovies.clearFocus()
                etSearchMyMovies.text = null
                requireView().hideKeyboard()
            }

            etSearchMyMovies.doAfterTextChanged {
                (binding.rvMyMoviesGenreAction.adapter as? MyMoviesAdapter)?.filterByTitle(it.toString())
                (binding.rvMyMoviesGenreAnimation.adapter as? MyMoviesAdapter)?.filterByTitle(it.toString())
                (binding.rvMyMoviesGenreDrama.adapter as? MyMoviesAdapter)?.filterByTitle(it.toString())
                (binding.rvMyMoviesGenreScienceFiction.adapter as? MyMoviesAdapter)?.filterByTitle(
                    it.toString())
            }

            setupMyMovieByGenreLists()

            movieViewModel.getMyMovies()
        }
    }

    override fun addObservers() {
        super.addObservers()

        movieViewModel.allMyMovies().observe(viewLifecycleOwner) {
            it?.let { allMyMovies ->
                val emptyMovie = MovieModel(genreIds = emptyList(),
                    title = "",
                    voteAverage = -1f,
                    posterPath = "")
                val actionMovies = listOf(emptyMovie) + allMyMovies.filter { movie ->
                    movie.genreIds.contains(ACTION_ID)
                }
                val animationMovies = listOf(emptyMovie) + allMyMovies.filter { movie ->
                    movie.genreIds.contains(ANIMATION_ID)
                }
                val dramaMovies = listOf(emptyMovie) +
                        allMyMovies.filter { movie -> movie.genreIds.contains(DRAMA_ID) }
                val scienceFictionMovies = listOf(emptyMovie) +
                        allMyMovies.filter { movie -> movie.genreIds.contains(SCIENCE_FICTION_ID) }

                binding.apply {
                    (rvMyMoviesGenreAction.adapter as? MyMoviesAdapter)?.submitList(actionMovies)
                    (rvMyMoviesGenreAnimation.adapter as? MyMoviesAdapter)?.submitList(
                        animationMovies)
                    (rvMyMoviesGenreDrama.adapter as? MyMoviesAdapter)?.submitList(dramaMovies)
                    (rvMyMoviesGenreScienceFiction.adapter as? MyMoviesAdapter)?.submitList(
                        scienceFictionMovies)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            movieViewModel.isMovieRemoved().collectLatest {
                movieViewModel.getMyMovies()
            }
        }
    }

    private fun FragmentMyMoviesBinding.setupMyMovieByGenreLists() {
        rvMyMoviesGenreAction.adapter =
            MyMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    if (requireActivity().isNetworkAvailable()) {
                        NewMoviesBottomSheetDialogFragment(ACTION_ID).show(parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    (binding.rvMyMoviesGenreAction.adapter as? MyMoviesAdapter)?.let { adapter ->
                        movieViewModel.removeMovieFromMyList(item)
                        adapter.removeAt(position)
                    }
                }
            })

        rvMyMoviesGenreAnimation.adapter =
            MyMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    if (requireActivity().isNetworkAvailable()) {
                        NewMoviesBottomSheetDialogFragment(ANIMATION_ID).show(
                            parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    (binding.rvMyMoviesGenreAnimation.adapter as? MyMoviesAdapter)?.let { adapter ->
                        movieViewModel.removeMovieFromMyList(item)
                        adapter.removeAt(position)
                    }
                }
            })

        rvMyMoviesGenreDrama.adapter =
            MyMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    if (requireActivity().isNetworkAvailable()) {
                        NewMoviesBottomSheetDialogFragment(DRAMA_ID).show(parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    (binding.rvMyMoviesGenreDrama.adapter as? MyMoviesAdapter)?.let { adapter ->
                        movieViewModel.removeMovieFromMyList(item)
                        adapter.removeAt(position)
                    }
                }
            })

        rvMyMoviesGenreScienceFiction.adapter =
            MyMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    if (requireActivity().isNetworkAvailable()) {
                        NewMoviesBottomSheetDialogFragment(SCIENCE_FICTION_ID).show(
                            parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    (binding.rvMyMoviesGenreScienceFiction.adapter as? MyMoviesAdapter)?.let { adapter ->
                        movieViewModel.removeMovieFromMyList(item)
                        adapter.removeAt(position)
                    }
                }
            })
    }

}