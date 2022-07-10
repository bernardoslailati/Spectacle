package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.spectacle.data.remote.enum.MovieGenresEnum.*
import com.slailati.android.spectacle.databinding.FragmentMyMoviesBinding
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.ui.base.BaseFragment
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.extension.isNetworkAvailable
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
                binding.rvMyMoviesGenreAction.myMoviesAdapter()?.filterByTitle(it.toString())
                binding.rvMyMoviesGenreAnimation.myMoviesAdapter()?.filterByTitle(it.toString())
                binding.rvMyMoviesGenreDrama.myMoviesAdapter()?.filterByTitle(it.toString())
                binding.rvMyMoviesGenreScienceFiction.myMoviesAdapter()?.filterByTitle(
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
                val myMoviesByGenre = createMyMoviesListsByGenre(allMyMovies)

                binding.apply {
                    rvMyMoviesGenreAction.myMoviesAdapter()?.submitList(myMoviesByGenre[ACTION.id])
                    rvMyMoviesGenreAnimation.myMoviesAdapter()
                        ?.submitList(myMoviesByGenre[ANIMATION.id])
                    rvMyMoviesGenreDrama.myMoviesAdapter()?.submitList(myMoviesByGenre[DRAMA.id])
                    rvMyMoviesGenreScienceFiction.myMoviesAdapter()
                        ?.submitList(myMoviesByGenre[SCIENCE_FICTION.id])
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            movieViewModel.isMovieRemoved().collectLatest {
                movieViewModel.getMyMovies()
            }
        }
    }

    private fun RecyclerView.myMoviesAdapter(): MyMoviesAdapter? {
        return this.adapter as? MyMoviesAdapter
    }

    private fun createMyMoviesListsByGenre(allMyMovies: List<MovieModel>): Map<Int, List<MovieModel>> {
        val addNewMovie = createAddNewMovieInstance()
        val myMoviesByGenreMap: MutableMap<Int, List<MovieModel>> = mutableMapOf()
        values().forEach { movieGenre ->
            myMoviesByGenreMap[movieGenre.id] = listOf(addNewMovie) + allMyMovies.filter { movie ->
                movie.genreIds.contains(movieGenre.id)
            }
        }
        return myMoviesByGenreMap
    }

    private fun createAddNewMovieInstance(): MovieModel {
        return MovieModel(
            genreIds = emptyList(),
            title = "",
            voteAverage = -1f,
            posterPath = ""
        )
    }

    private fun FragmentMyMoviesBinding.setupMyMovieByGenreLists() {
        rvMyMoviesGenreAction.adapter =
            MyMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    if (requireActivity().isNetworkAvailable()) {
                        NewMoviesBottomSheetDialogFragment(ACTION.id).show(parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    binding.rvMyMoviesGenreAction.myMoviesAdapter()?.let { adapter ->
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
                        NewMoviesBottomSheetDialogFragment(ANIMATION.id).show(
                            parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    binding.rvMyMoviesGenreAnimation.myMoviesAdapter()?.let { adapter ->
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
                        NewMoviesBottomSheetDialogFragment(DRAMA.id).show(parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    binding.rvMyMoviesGenreDrama.myMoviesAdapter()?.let { adapter ->
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
                        NewMoviesBottomSheetDialogFragment(SCIENCE_FICTION.id).show(
                            parentFragmentManager,
                            NewMoviesBottomSheetDialogFragment.TAG)
                    }
                }

                override fun onLongClick(item: MovieModel, position: Int) {
                    super.onLongClick(item, position)
                    binding.rvMyMoviesGenreScienceFiction.myMoviesAdapter()?.let { adapter ->
                        movieViewModel.removeMovieFromMyList(item)
                        adapter.removeAt(position)
                    }
                }
            })
    }

}