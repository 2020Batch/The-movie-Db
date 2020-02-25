package com.example.themoviedb.recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.common.IMG_BASE_URL_POPULAR_PEOPLE
import com.example.themoviedb.model.PopularPeopleResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_people_item.view.*

class PopularPeopleAdapter(private val popularPeople: PopularPeopleResponse) :
    RecyclerView.Adapter<PopularPeopleAdapter.PopularPeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPeopleViewHolder {
        return PopularPeopleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.popular_people_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return popularPeople.results.size
    }

    override fun onBindViewHolder(holder: PopularPeopleViewHolder, position: Int) {
        holder.personName.text = popularPeople.results[position].name
        holder.popularityScore.text = popularPeople.results[position].popularity.toString()
        Picasso.get()
            .load(IMG_BASE_URL_POPULAR_PEOPLE + popularPeople.results[position].profilePath)
            .into(holder.personImage)
    }

    inner class PopularPeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.tv_person_name_content
        val personImage: ImageView = itemView.img_popular_people
        val popularityScore: TextView = itemView.popularity_score_value
    }
}