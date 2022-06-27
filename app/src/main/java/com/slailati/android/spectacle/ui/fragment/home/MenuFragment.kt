package com.slailati.android.spectacle.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.FragmentMenuBinding
import com.slailati.android.spectacle.ui.fragment.BaseFragment
import com.slailati.android.spectacle.ui.fragment.dialog.SpectacleDialogFragment
import com.slailati.android.spectacle.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuFragment : BaseFragment() {

    private val userViewModel: UserViewModel by sharedViewModel()

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

            ivLogout.setOnClickListener {
                showLogoutAlertDialog()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        showLogoutAlertDialog()
        return true
    }

    private fun showLogoutAlertDialog() {
        SpectacleDialogFragment(
            title = "Deseja realmente sair?",
            content = "Ao sair, o logout será efetuado automaticamente.",
            onPositiveButtonClick = {
                userViewModel.logout()
                findNavController().setGraph(R.navigation.nav_login_graph)
            }
        ).show(parentFragmentManager, SpectacleDialogFragment.TAG)
    }

}