package com.example.themoviedb.Feature07

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.feature07.IMAGE_BASE
import com.example.themoviedb.feature07.PopularMoviesRecyclerViewClickListener
import com.example.themoviedb.feature07.model.PopularMovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_movie_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class PopularMoviesAdapter(
    private val popularMoviesModel: PopularMovieModel,
    private val clickListener: PopularMoviesRecyclerViewClickListener
) : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    private val picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
        return PopularMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularMoviesModel.results.size
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {

        holder.tvMovieTitle.text = popularMoviesModel.results[position].title
        holder.tvMovieReleaseDate.text = dateReverser(popularMoviesModel.results[position].release_date)
        holder.setOnClickListener(popularMoviesModel.results[position].id)
        picasso.load(IMAGE_BASE + popularMoviesModel.results[position].poster_path).into(holder.ivMoviePoster)
    }

    inner class PopularMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMoviePoster: ImageView = itemView.iv_moviePoster
        val tvMovieTitle: TextView = itemView.tv_movieName
        val tvMovieReleaseDate: TextView = itemView.tv_movieReleaseDate

        fun setOnClickListener(movieId: Int){
            itemView.setOnClickListener{
                clickListener.onItemClickedListener(movieId)

            }
        }

    }

    private fun dateReverser(date: String): String{
        val originalFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
        val targetFormat = SimpleDateFormat("dd-mm-yyyy")
        val newDate = originalFormat.parse(date)
        val formattedDate = targetFormat.format(newDate)
        return formattedDate
    }


}

