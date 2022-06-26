package com.slailati.android.spectacle.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.FragmentMenuBinding
import com.slailati.android.spectacle.ui.fragment.BaseFragment

class MenuFragment : BaseFragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            tvMyMusics.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_myMusicPlaylistFragment)
            }

            tvMyMovies.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_myMoviesFragment)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle("Deseja realmente sair?")
            .setMessage("Ao sair, o logout será efetuado automaticamente.")
            .setPositiveButton("SIM") { v, _ ->
                v.dismiss()
                requireActivity().finish()
            }
            .setNegativeButton("NÃO") { v, _ ->
                v.dismiss()
            }.show()

        return true
    }

}