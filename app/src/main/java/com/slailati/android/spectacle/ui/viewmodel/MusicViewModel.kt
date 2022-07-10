package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.slailati.android.spectacle.domain.repository.MusicRepository
import com.slailati.android.spectacle.domain.model.MusicModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MusicViewModel(
    private val musicRepository: MusicRepository
): BaseViewModel() {

    private val _allNewMusics: MutableLiveData<List<MusicModel>> = MutableLiveData()
    fun allNewMusics() = _allNewMusics

    private val _allMyMusicsPlaylist: MutableLiveData<List<MusicModel>> = MutableLiveData()
    fun allMyMusicsPlaylist() = _allMyMusicsPlaylist

    private val _isMusicAdded: MutableSharedFlow<Boolean> = MutableSharedFlow()
    fun isMusicAdded() = _isMusicAdded.asSharedFlow()

    private val _isMusicRemoved: MutableSharedFlow<Unit> = MutableSharedFlow()
    fun isMusicRemoved() = _isMusicRemoved.asSharedFlow()

    fun getNewMusics() {
        CoroutineScope(Dispatchers.IO).launch {
            _allNewMusics.postValue(musicRepository.getNewMusics())
        }
    }

    fun getMyMusicsPlaylist() {
        CoroutineScope(Dispatchers.IO).launch {
            _allMyMusicsPlaylist.postValue( musicRepository.getMyMusicsPlaylist())
        }
    }

    fun addMusicToMyPlaylist(music: MusicModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = musicRepository.insertMusicToMyPlaylist(music)
            _isMusicAdded.emit(result)
        }
    }

    fun removeMusicFromMyPlaylist(music: MusicModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = musicRepository.deleteMusicFromMyPlaylist(music.localId)
            _isMusicRemoved.emit(result)
        }
    }

}