package mx.com.qualitycode.moviesapp.di


import mx.com.qualitycode.moviesapp.api.MoviesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModules = module {
    // Single instance of MoviesRepository during the app lifecycle
    single { MoviesRepository(androidContext()) }
}