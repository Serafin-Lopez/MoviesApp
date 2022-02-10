package mx.com.qualitycode.moviesapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_default.*
import mx.com.qualitycode.moviesapp.R
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import mx.com.qualitycode.moviesapp.ui.adapters.PlayNowMovieCallback
import mx.com.qualitycode.moviesapp.ui.adapters.PlayNowMoviesAdapter
import mx.com.qualitycode.moviesapp.ui.viewmodels.DetailMovieViewModel
import mx.com.qualitycode.moviesapp.ui.viewmodels.MoviePlayNowDBViewModel
import mx.com.qualitycode.moviesapp.ui.viewmodels.PlayNowMoviesViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(), PlayNowMovieCallback {


    lateinit var adapter: PlayNowMoviesAdapter
    lateinit var viewModel: PlayNowMoviesViewModel
    lateinit var detailViewModel: DetailMovieViewModel
    lateinit var userDBViewModel: MoviePlayNowDBViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            adapter = PlayNowMoviesAdapter().apply { listCallback = this@HomeFragment }
            viewModel = ViewModelProvider(it)[PlayNowMoviesViewModel::class.java]
            detailViewModel = ViewModelProvider(it)[DetailMovieViewModel::class.java]
            userDBViewModel = ViewModelProvider(it)[MoviePlayNowDBViewModel::class.java]
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {

        recyclerViewPlayNow.adapter = adapter
        recyclerViewPlayNow.set3DItem(true)
        recyclerViewPlayNow.setAlpha(true)

        viewModel.liveData.observe(viewLifecycleOwner, Observer { list ->

            list?.let {
                adapter.setData(it)
                userDBViewModel.saveMovies(it)
            }

        })

        viewModel.loadPlayNowMovies()
    }



    override fun onSelected(movie: PopularMoviesModel.Result) {
        detailViewModel.detailMovie.value = movie
        replaceFragmentWith(DetailMovieFragment())
    }


}