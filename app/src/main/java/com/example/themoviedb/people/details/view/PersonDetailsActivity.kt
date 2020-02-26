package com.example.themoviedb.people.details.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.R
import com.example.themoviedb.common.BASE_IMAGE_URL
import com.example.themoviedb.common.network.model.PersonDetailModel
import com.example.themoviedb.people.details.model.PersonDetailsRepositoryImpl
import com.example.themoviedb.people.details.viewmodel.PersonDetailsViewModel
import com.example.themoviedb.people.details.viewmodel.PersonDetailsViewModelFactory
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_person_details.*
import kotlinx.android.synthetic.main.activity_popular_people.*
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.popular_people_item.*

class PersonDetailsActivity : AppCompatActivity() {
    private lateinit var personalDetailsViewModel: PersonDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        title = getString(R.string.txt_title_person_details)

        val viewModelFactory = PersonDetailsViewModelFactory(PersonDetailsRepositoryImpl())
        val personalDetailsViewModel: PersonDetailsViewModel
        personalDetailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(PersonDetailsViewModel::class.java)

        personalDetailsViewModel.fetchPersonDetail(556435)

        personalDetailsViewModel.repositoryPersonDetail.observe(this, Observer { personDetails ->
            people_details_error.visibility = View.GONE
            pb_person_details.visibility = View.GONE
            bindData(personDetails)


        })

        personalDetailsViewModel.errorPersonDetail.observe(this, Observer { fetchError ->
            people_details_error.visibility = View.VISIBLE
            tv_people_details_error_message.text = fetchError

            btn_people_details_retry.setOnClickListener {
                personalDetailsViewModel.fetchPersonDetail(556435)

            }

        })

    }

    private fun bindData(personDetailModel: PersonDetailModel) {
        tv_popular_person_name.text = personDetailModel.name
        tv_popular_person_bio.text = personDetailModel.biography
        tv_popular_person_rating.text = personDetailModel.popularity.toString()
        val imgUrl = BASE_IMAGE_URL + personDetailModel.profilePath

        Picasso
            .get()
            .load(imgUrl)
            .into(iv_popular_person_image)
    }
}
