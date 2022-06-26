package com.slailati.android.spectacle.ui.extension

import android.app.Activity
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager

fun View.gone() {
    if (this.visibility != GONE) this.visibility = GONE
}

fun View.invisible() {
    if (this.visibility != INVISIBLE) this.visibility = INVISIBLE
}

fun View.visible() {
    if (this.visibility != VISIBLE) this.visibility = VISIBLE
}

fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}
