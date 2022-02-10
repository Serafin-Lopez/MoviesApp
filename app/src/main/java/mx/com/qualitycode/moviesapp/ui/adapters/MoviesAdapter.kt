package mx.com.qualitycode.moviesapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movies_list.view.*
import mx.com.qualitycode.moviesapp.R
import mx.com.qualitycode.moviesapp.api.models.PopularMoviesModel
import mx.com.qualitycode.moviesapp.ui.helpers.loadImage
import mx.com.qualitycode.moviesapp.ui.helpers.loadText

const val BASE_URL_FOR_IMAGE = "https://image.tmdb.org/t/p/original"
class PlayNowMoviesAdapter : RecyclerView.Adapter<PlayNowMoviesAdapter.AdapterViewHolder>() {

    private var data: List<PopularMoviesModel.Result> = ArrayList()
    var listCallback: PlayNowMovieCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movies_list, parent, false),
            listCallback
        )
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: AdapterViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(data[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<PopularMoviesModel.Result>) {
        this.data = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<PopularMoviesModel.Result>) {
        this.data = data
        notifyDataSetChanged()
    }


    class AdapterViewHolder(itemView: View, var playNowMovieCallback: PlayNowMovieCallback?) : RecyclerView.ViewHolder(itemView) {

        private var data: PopularMoviesModel.Result?= null

        fun bind(item: PopularMoviesModel.Result) = with(itemView) {

            imageViewMovie.loadImage(BASE_URL_FOR_IMAGE+ item.posterPath)
            textViewTitle.loadText(item.title)
            data = item

            setOnClickListener {
                selectItem()
            }



        }

        /**
         * this function was created to select the item in recyclerView
         */

        fun selectItem() {
            data?.let {
                playNowMovieCallback?.onSelected(it)
            }
        }

    }

}

interface PlayNowMovieCallback {
    fun onSelected(movie: PopularMoviesModel.Result)
}