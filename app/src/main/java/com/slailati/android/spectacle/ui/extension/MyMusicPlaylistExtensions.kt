package com.slailati.android.spectacle.ui.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.slailati.android.spectacle.R
import com.slailati.android.spectacle.databinding.FragmentMyMusicPlaylistBinding

fun FragmentMyMusicPlaylistBinding.setAlbumCoverPreviews(albumCoverUrls: List<String>) {
    with(this) {
        clearAllAlbumPreviewViews()
        albumCoverUrls.forEachIndexed { index, albumCoverUrl ->
            if (index > 3) return
            val ivAlbumCoverPreview =
                this.root.findViewById<AppCompatImageView>(flwAlbumCoverPreviews.referencedIds[index])
            Glide
                .with(root)
                .load(albumCoverUrl)
                .placeholder(R.raw.loading_album_cover)
                .error(R.drawable.ic_not_found_album)
                .centerCrop()
                .into(ivAlbumCoverPreview
                )
            ivAlbumCoverPreview.visible()
        }
    }
}

private fun FragmentMyMusicPlaylistBinding.clearAllAlbumPreviewViews() {
    ivFirstAlbumCoverPreview.apply {
        setImageDrawable(null)
        gone()
    }
    ivSecondAlbumCoverPreview.apply {
        setImageDrawable(null)
        gone()
    }
    ivThirdAlbumCoverPreview.apply {
        setImageDrawable(null)
        gone()
    }
    ivFourthAlbumCoverPreview.apply {
        setImageDrawable(null)
        gone()
    }
}