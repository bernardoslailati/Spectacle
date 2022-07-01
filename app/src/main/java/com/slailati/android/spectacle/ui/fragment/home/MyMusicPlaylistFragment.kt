package com.slailati.android.spectacle.ui.fragment.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.FragmentMyMusicPlaylistBinding
import com.slailati.android.spectacle.ui.extension.hideKeyboard
import com.slailati.android.spectacle.ui.extension.isNetworkAvailable
import com.slailati.android.spectacle.ui.extension.setAlbumCoverPreviews
import com.slailati.android.spectacle.ui.fragment.BaseFragment
import com.slailati.android.spectacle.ui.utils.adapter.MyMusicsPlaylistAdapter
import com.slailati.android.spectacle.ui.viewmodel.MusicViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyMusicPlaylistFragment : BaseFragment() {

    private val musicViewModel: MusicViewModel by sharedViewModel()

    private var _binding: FragmentMyMusicPlaylistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyMusicPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            (binding.rvMyMusicPlaylist.adapter as? MyMusicsPlaylistAdapter)?.let { adapter ->
                musicViewModel.removeMusicFromMyPlaylist(adapter.currentList[position])
                adapter.removeAt(position)
            }
        }

        override fun onChildDraw(
            c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
            dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean,
        ) {
            val paint = Paint()
            val icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)!!
            icon.setTint(Color.WHITE)

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            if (dX != 0f && isCurrentlyActive) {
                val itemView = viewHolder.itemView
                paint.color =
                    ContextCompat.getColor(requireContext(), android.R.color.holo_red_light)
                val top = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
                val left =
                    itemView.width - icon.intrinsicWidth - (itemView.height - icon.intrinsicHeight) / 2
                val right = left + icon.intrinsicHeight
                val bottom = top + icon.intrinsicHeight

                if (dX < 0 && dX > -220) {
                    val background = RectF(itemView.right + dX,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat())
                    c.drawRect(background, paint)
                } else {
                    val background = RectF(itemView.right - 220f,
                        itemView.top.toFloat(),
                        itemView.right.toFloat(),
                        itemView.bottom.toFloat())
                    c.drawRect(background, paint)
                    icon.setBounds(left, top, right, bottom)
                }
                icon.draw(c)
            }
        }
    }

    override fun setupView() {
        super.setupView()

        with(binding) {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }

            clContent.setOnClickListener {
                etSearchPlaylistMusic.clearFocus()
                etSearchPlaylistMusic.text = null
                requireView().hideKeyboard()
            }

            tvAddNewMusic.setOnClickListener {
                if (requireActivity().isNetworkAvailable()) {
                    NewMusicsBottomSheetDialogFragment().show(parentFragmentManager,
                        NewMusicsBottomSheetDialogFragment.TAG)
                }
            }

            etSearchPlaylistMusic.doAfterTextChanged {
                (binding.rvMyMusicPlaylist.adapter as? MyMusicsPlaylistAdapter)?.filterByTitle(it.toString())
            }

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(rvMyMusicPlaylist)
            rvMyMusicPlaylist.adapter = MyMusicsPlaylistAdapter()

            musicViewModel.getMyMusicsPlaylist()
        }
    }

    override fun addObservers() {
        super.addObservers()

        musicViewModel.allMyMusicsPlaylist().observe(viewLifecycleOwner) {
            it?.let { myMusicsPlaylist ->
                (binding.rvMyMusicPlaylist.adapter as? MyMusicsPlaylistAdapter)?.submitList(
                    myMusicsPlaylist)
                binding.setAlbumCoverPreviews(myMusicsPlaylist.map { myMusic -> myMusic.albumCoverUrl })
            }
        }

        lifecycleScope.launchWhenStarted {
            musicViewModel.isMusicRemoved().collectLatest {
                musicViewModel.getMyMusicsPlaylist()
            }
        }
    }

}