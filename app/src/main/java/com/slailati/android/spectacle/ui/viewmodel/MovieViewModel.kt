package com.slailati.android.spectacle.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.slailati.android.spectacle.data.repository.MovieRepository
import com.slailati.android.spectacle.domain.model.MovieModel
import com.slailati.android.spectacle.domain.model.MusicModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    companion object {
        const val TOTAL_PAGES_SEARCHED = 5
    }

    private val _allMyMovies: MutableLiveData<List<MovieModel>> = MutableLiveData()
    fun allMyMovies() = _allMyMovies

    private val _isMovieAdded: MutableSharedFlow<Boolean> = MutableSharedFlow()
    fun isMovieAdded() = _isMovieAdded.asSharedFlow()

    private val _isMovieRemoved: MutableSharedFlow<Unit> = MutableSharedFlow()
    fun isMovieRemoved() = _isMovieRemoved.asSharedFlow()

    fun getNewMoviesByGenre(genreId: Int): Flow<List<MovieModel>> {
        return flow {
            repeat(TOTAL_PAGES_SEARCHED) {
                emit(movieRepository.getMovieByGenreId(page = it + 1, genreId))
            }
        }
    }

    fun getMyMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            _allMyMovies.postValue(movieRepository.getMyMovies())
        }
    }

    fun addMovieToMyMovies(genreId: Int, movie: MovieModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _isMovieAdded.emit(movieRepository.insertMovieToMyList(genreId, movie))
        }
    }

    fun removeMovieFromMyList(movie: MovieModel) {
        CoroutineScope(Dispatchers.IO).launch {
            _isMovieRemoved.emit(movieRepository.deleteMovieFromMyList(movie.localId))
        }
    }

}