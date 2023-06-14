package com.example.laenderapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.QuizQuestion
import com.example.laenderapp.data.datamodels.QuizResult
import com.example.laenderapp.data.local.getDatabase
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.launch

// Die Variable definiert eine Konstante mit dem Namen TAG, die den Wert "MainViewModel" hat. Und wird verwendet, um Lognachrichten zu identifizieren und zu filtern.
const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Erstellung eines AppRepository-Objekts unter Verwendung des CountryApi und der Datenbank aus der Anwendung
    private val repository = AppRepository(CountryApi, getDatabase(context = application))

    // Eine verschachtelte Variable für den Benutzer wird erstellt
    private var _userName = ""
    val userName: String
        get() = _userName

    // Funktion zum Festlegen des Benutzernamens
    fun setUserName(userName: String) {
        _userName = userName
    }

    // LiveData-Objekte für Länderlisten nach Kontinenten
    val europeCountriesLiveData = repository.europeCountries
    val asiaCountriesLiveData = repository.asiaCountries
    val africaCountriesLiveData = repository.africaCountries
    val northAmericaCountriesLiveData = repository.northAmericaCountries
    val southAmericaCountriesLiveData = repository.southAmericaCountries
    val oceaniaCountriesLiveData = repository.oceaniaCountries

    // LiveData-Objekt für ausgewählte Länderliste (Standard: Europa)
    var selectedCountriesLiveData = europeCountriesLiveData

    // Eine Liste von Quizfragen
    private val quizQuestions = mutableListOf<QuizQuestion>()

    // Funktion zum Abrufen der Kontinentenliste
    fun continentsList() {
        viewModelScope.launch { repository.getContinents() }
    }

    // Funktion zum Einfügen eines Quizergebnisses
    fun insertQuiz(quizResult: QuizResult) {
        viewModelScope.launch { repository.insertQuizResult(quizResult) }
    }

    // Funktion zum Auswählen einer Länderliste basierend auf dem ausgewählten Kontinent
    fun selectContinent(continent: String) {
        when (continent) {
            "europe" -> selectedCountriesLiveData = europeCountriesLiveData
            "asia" -> selectedCountriesLiveData = asiaCountriesLiveData
            "africa" -> selectedCountriesLiveData = africaCountriesLiveData
            "northAmerica" -> selectedCountriesLiveData = northAmericaCountriesLiveData
            "southAmerica" -> selectedCountriesLiveData = southAmericaCountriesLiveData
            "oceania" -> selectedCountriesLiveData = oceaniaCountriesLiveData

            else -> Log.e(TAG, "Auswahl des Kontinents: $continent fehlgeschlagen")
        }
    }
}