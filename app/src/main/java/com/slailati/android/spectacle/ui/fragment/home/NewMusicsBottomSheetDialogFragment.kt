package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.slailati.android.spectacle.databinding.BottomsheetDialogFragmentNewMusicsBinding
import com.slailati.android.spectacle.domain.model.MusicModel
import com.slailati.android.spectacle.ui.extension.gone
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.extension.observeOnce
import com.slailati.android.spectacle.ui.fragment.BaseBottomSheetDialogFragment
import com.slailati.android.spectacle.ui.utils.adapter.NewMusicsAdapter
import com.slailati.android.spectacle.ui.utils.adapter.OnItemClickListener
import com.slailati.android.spectacle.ui.viewmodel.MyMusicPlaylistViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewMusicsBottomSheetDialogFragment : BaseBottomSheetDialogFragment() {

    private val myMusicPlaylistViewModel: MyMusicPlaylistViewModel by sharedViewModel()

    private var _binding: BottomsheetDialogFragmentNewMusicsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "NewMusicsBottomSheetDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomsheetDialogFragmentNewMusicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            ivClose.setOnClickListener {
                dialog?.dismiss()
            }

            clContent.setOnClickListener {
                etSearchNewMusic.clearFocus()
                requireView().hideKeyboard()
            }

            rvNewMusics.adapter = NewMusicsAdapter(object : OnItemClickListener<MusicModel> {
                override fun onAddButtonClick(item: MusicModel) {
                    super.onAddButtonClick(item)
                    myMusicPlaylistViewModel.allMyMusicsPlaylist().value?.let { myMusics ->
                        if (myMusics.any { it.title == item.title }) {
                            Toast.makeText(requireContext(),
                                "A música escolhida já existe na sua playlist.",
                                Toast.LENGTH_SHORT).show()
                            return@onAddButtonClick
                        }
                    }
                    myMusicPlaylistViewModel.addMusicToMyPlaylist(item)
                }
            })
        }
    }

    override fun addObservers() {
        super.addObservers()

        myMusicPlaylistViewModel.getNewMusics()

        myMusicPlaylistViewModel.allNewMusics().observe(viewLifecycleOwner) {
            it?.let { allMusics ->
                binding.lavLoadingNewMusics.apply {
                    pauseAnimation()
                    gone()
                }
                (binding.rvNewMusics.adapter as? NewMusicsAdapter)?.submitList(allMusics)
            }
        }

        lifecycleScope.launchWhenStarted {
            myMusicPlaylistViewModel.isMusicAdded().collectLatest { isAdded ->
                if (isAdded) {
                    myMusicPlaylistViewModel.getMyMusicsPlaylist()
                    Toast.makeText(
                        requireActivity(),
                        "A música foi adicionada a sua playlist.",
                        Toast.LENGTH_LONG
                    ).show()
                    dialog?.dismiss()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Erro ao adicionar a música em sua playlist. Por favor, tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}