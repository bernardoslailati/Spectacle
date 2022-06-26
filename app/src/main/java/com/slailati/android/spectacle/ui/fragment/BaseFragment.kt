package com.slailati.android.spectacle.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setupView()
    }

    open fun addObservers() {}

    open fun setupView() {}

    open fun onBackPressed(): Boolean {
        return false
    }

}