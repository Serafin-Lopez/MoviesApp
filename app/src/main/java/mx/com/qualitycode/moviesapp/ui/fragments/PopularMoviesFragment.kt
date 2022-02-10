package mx.com.qualitycode.moviesapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import mx.com.qualitycode.moviesapp.R
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import mx.com.qualitycode.moviesapp.ui.adapters.PlayNowMovieCallback
import mx.com.qualitycode.moviesapp.ui.adapters.PlayNowMoviesAdapter
import mx.com.qualitycode.moviesapp.ui.viewmodels.DetailMovieViewModel
import mx.com.qualitycode.moviesapp.ui.viewmodels.PopularMoviesViewModel


class PopularMoviesFragment : BaseFragment(), PlayNowMovieCallback {


    lateinit var adapter: PlayNowMoviesAdapter
    lateinit var viewModel: PopularMoviesViewModel
    lateinit var detailViewModel: DetailMovieViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            adapter = PlayNowMoviesAdapter().apply { listCallback = this@PopularMoviesFragment }
            viewModel = ViewModelProvider(it)[PopularMoviesViewModel::class.java]
            detailViewModel = ViewModelProvider(it)[DetailMovieViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {

        recyclerViewPopularMovies.adapter = adapter
        recyclerViewPopularMovies.set3DItem(true)
        recyclerViewPopularMovies.setAlpha(true)

        viewModel.liveData.observe(viewLifecycleOwner, Observer { list ->

            list?.let {
                adapter.setData(it)
            }

        })

        viewModel.loadPopularMovies()
    }


    override fun onSelected(movie: PopularMoviesModel.Result) {
        detailViewModel.detailMovie.value = movie
        replaceFragmentWith(DetailMovieFragment())
    }

}