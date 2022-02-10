package mx.com.qualitycode.moviesapp.api.database.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import mx.com.qualitycode.moviesapp.api.database.entity.MoviePopularEntity
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel

@Dao
interface MoviePlayNowDao:BaseDao<PopularMoviesModel.Result> {

    @Delete
    fun delete(vararg moviePopular: PopularMoviesModel)

    @Query("SELECT * FROM " + MoviePopularEntity.TABLE_NAME + " ORDER BY username")
    fun getMovies(): MutableLiveData<List<MoviePopularEntity>>

}
