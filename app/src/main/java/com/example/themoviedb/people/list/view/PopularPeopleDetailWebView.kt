package com.example.themoviedb.people.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.themoviedb.R

class PopularPeopleDetailWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_people_item_web_view)

        val myWebView:WebView = findViewById(R.id.popular_people_item_web_view)
        myWebView.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: String?
            ): Boolean {

                view?.loadUrl(request)
                return true
            }
        }

        val people = intent.getSerializableExtra("EXTRA_PEOPLE_WEB")


        myWebView.loadUrl(people.toString())

    }


}
