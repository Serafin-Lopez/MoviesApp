package mx.com.qualitycode.moviesapp.api.database.repository


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

open class GenericRepository(val dispatcher: CoroutineDispatcher): CoroutineScope {

    companion object { const val TAG = "GenericRepository" }

    override val coroutineContext: CoroutineContext
        get() =  dispatcher
}