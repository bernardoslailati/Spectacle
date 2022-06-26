package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel: ViewModel(), LifecycleObserver, KoinComponent