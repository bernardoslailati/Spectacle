package com.slailati.android.spectacle.ui.fragment.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.slailati.android.spectacle.databinding.DialogBaseBinding
import com.slailati.android.spectacle.ui.extension.gone
import com.slailati.android.spectacle.ui.extension.visible
import com.slailati.android.spectacle.ui.fragment.BaseDialogFragment

class SpectacleDialogFragment(
    private val title: String = "",
    private val content: String = "",
    private val hasPositiveButton: Boolean = true,
    private val positiveButtonTitle: String = "Confirmar",
    private val onPositiveButtonClick: () -> Unit = {},
    private val hasNegativeButton: Boolean = true,
    private val negativeButtonTitle: String = "Cancelar",
    private val onNegativeButtonClick: () -> Unit = {},
) : BaseDialogFragment() {

    private var _binding: DialogBaseBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "SpectacleDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            tvTitle.text = title
            tvContent.text = content
            if (!hasPositiveButton)
                btnPositive.gone()
            else {
                btnPositive.apply {
                    visible()
                    text = positiveButtonTitle
                    setOnClickListener {
                        onPositiveButtonClick()
                        this@SpectacleDialogFragment.dismiss()
                    }
                }
            }
            if (!hasNegativeButton)
                btnNegative.gone()
            else {
                btnNegative.apply {
                    visible()
                    text = negativeButtonTitle
                    setOnClickListener {
                        onNegativeButtonClick()
                        this@SpectacleDialogFragment.dismiss()
                    }
                }
            }
        }
    }

}