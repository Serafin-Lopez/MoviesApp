package mx.com.qualitycode.moviesapp.api.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.qualitycode.moviesapp.api.database.dao.MoviePlayNowDao
import mx.com.qualitycode.moviesapp.api.database.entity.MoviePopularEntity

@Database(entities = [MoviePopularEntity::class], version = 1,exportSchema = false)

abstract class MoviesDataBase : RoomDatabase() {

    abstract fun moviePlayDao(): MoviePlayNowDao


    companion object {
        private const val DATABASE_NAME = "movie_database"
        @Volatile
        private var INSTANCE: MoviesDataBase? = null

        fun getInstance(context: Context): MoviesDataBase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }

        fun reloadDatabase(context: Context) {
            Room.databaseBuilder(context, MoviesDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}