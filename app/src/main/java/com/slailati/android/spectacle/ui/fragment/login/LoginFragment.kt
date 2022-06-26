package com.slailati.android.spectacle.ui.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.data.model.User
import com.slailati.android.spectacle.databinding.FragmentLoginBinding
import com.slailati.android.spectacle.ui.extension.*
import com.slailati.android.spectacle.ui.fragment.BaseFragment
import com.slailati.android.spectacle.ui.fragment.dialog.SpectacleDialogFragment
import com.slailati.android.spectacle.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment() {

    private val userViewModel: UserViewModel by sharedViewModel()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.resetErrorMessages()
    }

    override fun setupView() {
        super.setupView()

        userViewModel.checkIfIsAlreadyLogged()

        with(binding) {
            tvNotRegistered.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                if (requireActivity().isNetworkAvailable()) {
                    btnLogin.gone()
                    pbLoginLoading.visible()
                    if (isValidCredentials()) {
                        val loginUser = User(
                            email = etEmail.text.toString(),
                            password = etPassword.text.toString()
                        )
                        userViewModel.login(loginUser)
                        userViewModel.isLoggedIn().observeOnce(viewLifecycleOwner) {
                            it?.let { response ->
                                if (response.success) {
                                    userViewModel.insertProfile(response.value)
                                    findNavController().navigate(R.id.action_loginFragment_to_nav_home_graph)
                                } else
                                    Toast.makeText(requireContext(),
                                        response.message,
                                        Toast.LENGTH_LONG).show()
                                btnLogin.visible()
                                pbLoginLoading.gone()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun addObservers() {
        super.addObservers()

        lifecycleScope.launchWhenStarted {
            userViewModel.isAlreadyLoggedIn().collectLatest { isAlreadyLoggedIn ->
                if (isAlreadyLoggedIn)
                    findNavController().navigate(R.id.action_loginFragment_to_nav_home_graph)
            }
        }
    }

}