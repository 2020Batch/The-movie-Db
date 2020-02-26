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
import kotlinx.android.synthetic.main.popular_people_item.*

class PersonDetailsActivity : AppCompatActivity() {
    private lateinit var personalDetailsViewModel: PersonDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        val viewModelFactory = PersonDetailsViewModelFactory(PersonDetailsRepositoryImpl())
        val personalDetailsViewModel: PersonDetailsViewModel
        personalDetailsViewModel =   ViewModelProvider(this, viewModelFactory).get(PersonDetailsViewModel::class.java)

        personalDetailsViewModel.fetchPersonDetail(1)

        personalDetailsViewModel.repositoryPersonDetail.observe(this,Observer{
        personDetails ->
            pb_person_details.visibility= View.VISIBLE
            bindData(personDetails)
            pb_person_details.visibility= View.GONE

        })

        personalDetailsViewModel.errorPersonDetail.observe(this, Observer {

            people_details_error.visibility=View.VISIBLE

        })

    }
    /* personViewModel.fetchPopularPeople()


        personViewModel.getPopularPeople().observe(this, Observer { person ->
            rv_popular_people.layoutManager = LinearLayoutManager(this)
            pb_popular_people.visibility = View.GONE
            rv_popular_people.adapter =
                PopularPeopleAdapter(
                    person
                )
        })

        personViewModel.getFetchError().observe(this, Observer { fetchError ->
            pb_popular_people.visibility = View.GONE
            lil_popular_people_error.visibility = View.VISIBLE
            tv_popular_people_error_message.text = fetchError
            btn_popular_people_retry.setOnClickListener {
                pb_popular_people.visibility = View.VISIBLE
                lil_popular_people_error.visibility = View.GONE
                personViewModel.fetchPopularPeople()
            }
        })
    }*/
    private fun bindData(personDetailModel: PersonDetailModel) {
        tv_person_name_details.text = personDetailModel.name
        tv_person_biography_details.text = personDetailModel.biography
        popularity_score_value.text  = personDetailModel.popularity.toString()
        val imgUrl = BASE_IMAGE_URL + personDetailModel.profilePath

        Picasso
            .get()
            .load(imgUrl)
            .into(img_person_details)}


}
