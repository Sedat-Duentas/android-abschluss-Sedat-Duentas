package com.example.laenderapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.remotes.CountryApi

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(CountryApi)
}