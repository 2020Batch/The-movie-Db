package com.example.themoviedb.movies.detail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.common.BASE_IMAGE_URL
import com.example.themoviedb.common.network.model.SimilarMovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.similar_movies_card.view.*

class SimilarMoviesAdapter(
    private val similarMovies: SimilarMovieModel,
    private val onClickListener: RecyclerViewOnClick
) : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.similar_movies_card, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return similarMovies.listOfSimilarMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(
            BASE_IMAGE_URL + similarMovies
                .listOfSimilarMovies[position].posterPath
        )
            .into(holder.itemView.iv_similarMovies)
        holder.onBind(similarMovies.listOfSimilarMovies[position].id)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movieId: Int) {
            itemView.setOnClickListener {
                onClickListener.recyclerViewOnClickListener(movieId)
            }
        }
    }
}

