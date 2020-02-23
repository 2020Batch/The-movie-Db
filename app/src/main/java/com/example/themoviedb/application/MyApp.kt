package com.example.themoviedb.application

import android.app.Application
import android.content.Context

class MyApp:Application() {

    companion object{
        fun getApplicationContext():Context = this.getApplicationContext()
    }
}