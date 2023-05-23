package com.example.laenderapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.local.getDatabase
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(CountryApi, getDatabase(context = application))

    //val continents = repository.continents
    val continents: MutableLiveData<Continents> = MutableLiveData(
        Continents(
            europe = emptyList(),
            asia = emptyList(),
            africa = emptyList(),
            northAmerica = emptyList(),
            southAmerica = emptyList(),
            oceania = emptyList(),
            )
    )

    val europeCountriesLiveData = repository.europeCountries
    val asiaCountriesLiveData = repository.asiaCountries
    val africaCountriesLiveData = repository.africaCountriesCountries
    val northAmericaCountriesLiveData = repository.northAmericaCountries
    val southAmericaCountriesLiveData = repository.southAmericaCountries
    val oceaniaCountriesLiveData = repository.oceaniaCountries

    init {
        continentsList()
    }

    fun continentsList() {
        viewModelScope.launch { repository.getContinents() }
    }
}