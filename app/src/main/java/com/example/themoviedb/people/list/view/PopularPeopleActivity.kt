package com.example.themoviedb.people.list.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.common.POPULAR_PERSON_ID_KEY
import com.example.themoviedb.people.details.view.PersonDetailsActivity
import com.example.themoviedb.people.list.model.PopularPeopleRepositoryImpl
import com.example.themoviedb.people.list.viewmodel.PopularPeopleViewModel
import com.example.themoviedb.people.list.viewmodel.PopularPeopleViewModelFactory
import kotlinx.android.synthetic.main.activity_popular_people.*

class PopularPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_people)

        title = getString(R.string.txt_popular_people)

        val personViewModel = ViewModelProvider(
            this,
            PopularPeopleViewModelFactory(
                PopularPeopleRepositoryImpl()
            )
        ).get(
            PopularPeopleViewModel::class.java
        )
        personViewModel.fetchPopularPeople()


        personViewModel.getPopularPeople().observe(this, Observer { person ->
            rv_popular_people.layoutManager = LinearLayoutManager(this)
            pb_popular_people.visibility = View.GONE

            rv_popular_people.adapter =
                PopularPeopleAdapter(person, object :OnPersonRecyclerItemClicked{
                    override fun onRecyclerItemClicked(id: Int) {
                        val intent = Intent(this@PopularPeopleActivity, PersonDetailsActivity::class.java)
                        intent.putExtra(POPULAR_PERSON_ID_KEY, id)
                        startActivity(intent)
                    }
                })
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
    }
}
