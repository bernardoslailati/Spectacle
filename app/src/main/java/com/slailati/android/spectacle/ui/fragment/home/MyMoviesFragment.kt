package com.slailati.android.spectacle.ui.fragment.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.FragmentMyMoviesBinding
import com.slailati.android.spectacle.databinding.FragmentMyMusicPlaylistBinding
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.fragment.BaseFragment
import com.slailati.android.spectacle.ui.utils.adapter.MyMusicsPlaylistAdapter
import com.slailati.android.spectacle.ui.viewmodel.MyMusicPlaylistViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyMoviesFragment : BaseFragment() {


    private val myMusicPlaylistViewModel: MyMusicPlaylistViewModel by sharedViewModel()

    private var _binding: FragmentMyMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun addObservers() {
        super.addObservers()

    }


}