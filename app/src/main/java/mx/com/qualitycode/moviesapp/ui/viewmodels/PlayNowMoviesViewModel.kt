package mx.com.qualitycode.moviesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import mx.com.qualitycode.moviesapp.api.MoviesRepository
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayNowMoviesViewModel(application: Application) : BaseAndroidViewModel(application) {

    private val repository by inject<MoviesRepository>()
    val liveData: MutableLiveData<List<PopularMoviesModel.Result>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val status: MutableLiveData<Boolean> = MutableLiveData()

    init {
        liveData.value = listOf()
        error.value = null
        status.value = null
    }

    fun loadPlayNowMovies(){

        repository.getAPI().getNowPlayingMovies().enqueue(object: Callback<PopularMoviesModel> {

            override fun onResponse(call: Call<PopularMoviesModel>, response: Response<PopularMoviesModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.results.let { categories ->
                            liveData.postValue(categories)
                            status.value = true
                        }
                    }
                } else {
                    status.value = false
                }
            }

            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

}
