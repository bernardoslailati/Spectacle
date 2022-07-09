package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.BottomsheetDialogFragmentNewMusicsBinding
import com.slailati.android.spectacle.domain.model.MusicModel
import com.slailati.android.spectacle.ui.extension.gone
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.base.BaseBottomSheetDialogFragment
import com.slailati.android.spectacle.ui.extension.isNetworkAvailable
import com.slailati.android.spectacle.ui.extension.toast
import com.slailati.android.spectacle.ui.utils.adapter.NewMusicsAdapter
import com.slailati.android.spectacle.ui.utils.adapter.OnItemClickListener
import com.slailati.android.spectacle.ui.viewmodel.MusicViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewMusicsBottomSheetDialogFragment : BaseBottomSheetDialogFragment() {

    private val musicViewModel: MusicViewModel by sharedViewModel()

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
                etSearchNewMusic.text = null
                requireView().hideKeyboard()
            }

            etSearchNewMusic.doAfterTextChanged {
                (binding.rvNewMusics.adapter as? NewMusicsAdapter)?.filterByTitle(it.toString())
            }

            rvNewMusics.adapter = NewMusicsAdapter(object : OnItemClickListener<MusicModel> {
                override fun onAddButtonClick(item: MusicModel) {
                    super.onAddButtonClick(item)
                    musicViewModel.allMyMusicsPlaylist().value?.let { myMusics ->
                        if (myMusics.any { it.title == item.title }) {
                            requireContext().toast(getString(R.string.music_already_added))
                            return@onAddButtonClick
                        }
                    }
                    musicViewModel.addMusicToMyPlaylist(item)
                }
            })
        }
    }

    override fun addObservers() {
        super.addObservers()

        musicViewModel.getNewMusics()

        musicViewModel.allNewMusics().observe(viewLifecycleOwner) {
            it?.let { allMusics ->
                binding.lavLoadingNewMusics.apply {
                    pauseAnimation()
                    gone()
                }
                (binding.rvNewMusics.adapter as? NewMusicsAdapter)?.submitList(allMusics)
            }
        }

        lifecycleScope.launchWhenStarted {
            musicViewModel.isMusicAdded().collectLatest { isAdded ->
                if (isAdded) {
                    musicViewModel.getMyMusicsPlaylist()
                    dialog?.dismiss()
                } else
                    requireActivity().toast("Erro ao adicionar a música em sua playlist. Por favor, tente novamente.",
                        Toast.LENGTH_LONG)
            }
        }
    }

}