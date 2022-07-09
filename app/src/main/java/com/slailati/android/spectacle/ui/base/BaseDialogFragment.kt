package com.slailati.android.spectacle.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment: DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupView()
    }

    open fun addObservers() {}

    open fun setupView() {}

}