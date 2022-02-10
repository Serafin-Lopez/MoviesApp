package mx.com.qualitycode.moviesapp.api.database.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.qualitycode.moviesapp.api.database.dao.MoviePlayNowDao
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class MoviePlayNowRepository @Inject constructor(
    private val localDataSource: MoviePlayNowDao
): GenericRepository(Dispatchers.Main) {

    override val coroutineContext: CoroutineContext
        get() =  Dispatchers.Main

    fun setMovie(movie: List<PopularMoviesModel.Result>) {
        launch  { insertBackground(movie) }
    }

    private suspend fun insertBackground(movie: List<PopularMoviesModel.Result>){
        withContext(Dispatchers.IO){
            localDataSource.insertAll(movie)
        }
    }

}