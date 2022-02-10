package mx.com.qualitycode.moviesapp

import android.app.Application
import mx.com.qualitycode.moviesapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Base class for maintaining global application state.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()

    }

    /**
     * Initialize Koin Dependency Injection
     */
    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(listOf(appModules))
            koin.createRootScope()
        }
    }

}