package com.example.themoviedb.people.list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.common.BASE_IMAGE_URL
import com.example.themoviedb.common.network.model.PopularPeopleModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_people_item.view.*

class PopularPeopleAdapter(private val popularPeople: PopularPeopleModel) :
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
        return popularPeople.people.size
    }

    override fun onBindViewHolder(holder: PopularPeopleViewHolder, position: Int) {
        holder.personName.text = popularPeople.people[position].name
        holder.popularityScore.text = popularPeople.people[position].popularity.toString()
        Picasso.get()
            .load(BASE_IMAGE_URL + popularPeople.people[position].profilePath)
            .into(holder.personImage)
    }

    inner class PopularPeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView = itemView.tv_person_name_content
        val personImage: ImageView = itemView.img_popular_people
        val popularityScore: TextView = itemView.popularity_score_value
    }
}