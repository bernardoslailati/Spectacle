package com.slailati.android.spectacle.ui.utils.adapter

interface OnItemClickListener<T> {

    fun onAddButtonClick(item: T) {}

    fun onLongClick(item: T, position: Int) {}

}