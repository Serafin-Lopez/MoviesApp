package mx.com.qualitycode.moviesapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.qualitycode.moviesapp.api.database.repository.MoviePlayNowRepository
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel


class MoviePlayNowDBViewModel @ViewModelInject
constructor (private val repository: MoviePlayNowRepository) : ViewModel() {

    fun saveMovies(movies: List<PopularMoviesModel.Result>) {
        viewModelScope.launch {
            repository.setMovie(movies)
        }
    }

}