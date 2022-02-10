package mx.com.qualitycode.moviesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import mx.com.qualitycode.moviesapp.api.MoviesRepository
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel(application: Application) : BaseAndroidViewModel(application) {

    val detailMovie : MutableLiveData<PopularMoviesModel.Result> = MutableLiveData()

    init {
        detailMovie.value = null
    }


}
