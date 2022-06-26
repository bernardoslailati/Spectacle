package com.slailati.android.spectacle.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialogFragment(private val isExpanded: Boolean = true) :
    BottomSheetDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (isExpanded)
            setExpandedState(dialog)
        return dialog
    }

    private fun setExpandedState(dialog: Dialog) {
        dialog.setOnShowListener {
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupView()
    }

    open fun addObservers() {}

    open fun setupView() {}

}