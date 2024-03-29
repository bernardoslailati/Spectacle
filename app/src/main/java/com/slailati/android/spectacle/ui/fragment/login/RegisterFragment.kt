package com.slailati.android.spectacle.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.slailati.android.spectacle.data.remote.model.User
import com.slailati.android.spectacle.databinding.FragmentRegisterBinding
import com.slailati.android.spectacle.ui.base.BaseFragment
import com.slailati.android.spectacle.ui.extension.*
import com.slailati.android.spectacle.ui.fragment.dialog.SpectacleDialogFragment
import com.slailati.android.spectacle.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegisterFragment : BaseFragment() {

    private val userViewModel: UserViewModel by sharedViewModel()

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
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

        with(binding) {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnRegister.setOnClickListener {
                if (isValidCredentials()) {
                    btnRegister.gone()
                    pbRegisterLoading.visible()

                    val newUser =
                        User(email = etEmail.text.toString(), password = etPassword.text.toString())
                    userViewModel.registerUser(newUser)
                }
            }
        }
    }

    override fun addObservers() {
        super.addObservers()

        userViewModel.isUserRegistered().observe(viewLifecycleOwner) {
            it?.let { response ->
                with(binding) {
                    btnRegister.visible()
                    pbRegisterLoading.gone()
                }
                if (response.success) {
                    SpectacleDialogFragment(
                        title = "Usuário cadastrado com sucesso",
                        content = response.message,
                        hasPositiveButton = false,
                        negativeButtonTitle = "Fechar",
                        onNegativeButtonClick = {
                            findNavController().popBackStack()
                        }
                    ).show(parentFragmentManager, SpectacleDialogFragment.TAG)
                } else
                    binding.root.context.toast(response.message)
            }
        }
    }

}