package mx.com.qualitycode.moviesapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.toolbar_default.*
import mx.com.qualitycode.moviesapp.R
import mx.com.qualitycode.moviesapp.ui.adapters.BASE_URL_FOR_IMAGE
import mx.com.qualitycode.moviesapp.ui.helpers.loadImage
import mx.com.qualitycode.moviesapp.ui.helpers.loadText
import mx.com.qualitycode.moviesapp.ui.helpers.show
import mx.com.qualitycode.moviesapp.ui.viewmodels.DetailMovieViewModel


class DetailMovieFragment : BaseFragment() {

    lateinit var viewModel : DetailMovieViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewModel = ViewModelProvider(it)[DetailMovieViewModel::class.java]
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        backButton()
    }

    private fun setupViewModel() {
        viewModel.detailMovie.observe(requireActivity(), Observer { movie ->

            movie?.let {
                imageView?.loadImage(BASE_URL_FOR_IMAGE + it.posterPath)
                textViewTitle?.loadText(it.title)
                textViewOverview?.loadText(it.overview)
                textViewReleaseDate?.loadText(it.releaseDate)
                textViewVoteCount?.loadText(it.voteCount.toString())

                viewModel.loadMovie(it.id)
            }

        })

        viewModel.liveData.observe(requireActivity(), Observer { movie ->

            movie?.let {

                videoView?.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {

                        var videoId = ""
                        it.forEach {
                            if (it.key != "") {
                                videoId = it.key
                            }
                        }

                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        })

        lifecycle.addObserver(videoView)

        imageViewBack.show()
        imageViewBack.setOnClickListener {
            popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()

    }
}