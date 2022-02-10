package mx.com.qualitycode.moviesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import mx.com.qualitycode.moviesapp.api.MoviesRepository
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import mx.com.qualitycode.moviesapp.api.models.VideoModel
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieViewModel(application: Application) : BaseAndroidViewModel(application) {

    val detailMovie : MutableLiveData<PopularMoviesModel.Result> = MutableLiveData()
    val liveData: MutableLiveData<List<VideoModel.Result>> = MutableLiveData()
    private val repository by inject<MoviesRepository>()
    init {
        detailMovie.value = null
        liveData.value = null
    }


    fun loadMovie(id: Int){

        repository.getAPI().getMovie(id).enqueue(object: Callback<VideoModel> {

            override fun onResponse(call: Call<VideoModel>, response: Response<VideoModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.results.let { categories ->
                            liveData.postValue(categories)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<VideoModel>, t: Throwable) {

            }
        })
    }
}
