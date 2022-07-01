package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.slailati.android.spectacle.databinding.BottomsheetDialogFragmentNewMoviesBinding
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.ui.extension.gone
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.fragment.BaseBottomSheetDialogFragment
import com.slailati.android.spectacle.ui.utils.adapter.NewMoviesAdapter
import com.slailati.android.spectacle.ui.utils.adapter.OnItemClickListener
import com.slailati.android.spectacle.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewMoviesBottomSheetDialogFragment(private val genreId: Int) :
    BaseBottomSheetDialogFragment() {

    private val movieViewModel: MovieViewModel by sharedViewModel()

    private var _binding: BottomsheetDialogFragmentNewMoviesBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "NewMoviesBottomSheetDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomsheetDialogFragmentNewMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            ivClose.setOnClickListener {
                dialog?.dismiss()
            }

            clContent.setOnClickListener {
                etSearchNewMovie.clearFocus()
                etSearchNewMovie.text = null
                requireView().hideKeyboard()
            }

            etSearchNewMovie.doAfterTextChanged {
                (binding.rvNewMovies.adapter as? NewMoviesAdapter)?.filterByTitle(it.toString())
            }

            rvNewMovies.adapter = NewMoviesAdapter(object : OnItemClickListener<MovieModel> {
                override fun onAddButtonClick(item: MovieModel) {
                    super.onAddButtonClick(item)

                    movieViewModel.allMyMovies().value?.let { myMovies ->
                        if (myMovies.any { it.title == item.title && it.genreIds.contains(genreId) }) {
                            Toast.makeText(requireContext(),
                                "O filme escolhido já existe na sua lista.",
                                Toast.LENGTH_SHORT)
                                .show()
                            return@onAddButtonClick
                        }
                    }
                    movieViewModel.addMovieToMyMovies(genreId, item)
                }
            })
        }
    }

    override fun addObservers() {
        super.addObservers()

        lifecycleScope.launchWhenStarted {
            movieViewModel.getNewMoviesByGenre(genreId).collectLatest { newMovies ->
                if (newMovies.isNotEmpty()) {
                    binding.lavLoadingNewMovies.pauseAnimation()
                    binding.lavLoadingNewMovies.gone()

                    (binding.rvNewMovies.adapter as? NewMoviesAdapter)?.let { adapter ->
                        if (adapter.itemCount == 0)
                            adapter.submitList(newMovies)
                        else
                            adapter.addNewPage(newMovies)
                    }
                }
            }
        }

        lifecycleScope.launch {
            movieViewModel.isMovieAdded().collectLatest { isAdded ->
                if (isAdded) {
                    movieViewModel.getMyMovies()
                    dialog?.dismiss()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erro ao adicionar o filme em sua lista. Por favor, tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}