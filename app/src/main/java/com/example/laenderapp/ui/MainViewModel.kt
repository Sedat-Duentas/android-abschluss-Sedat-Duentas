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

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(CountryApi, getDatabase(context = application))


    val europeCountriesLiveData = repository.europeCountries
    val asiaCountriesLiveData = repository.asiaCountries
    val africaCountriesLiveData = repository.africaCountries
    val northAmericaCountriesLiveData = repository.northAmericaCountries
    val southAmericaCountriesLiveData = repository.southAmericaCountries
    val oceaniaCountriesLiveData = repository.oceaniaCountries

    var selectedCountriesLiveData = europeCountriesLiveData

    private val quizQuestions = mutableListOf<QuizQuestion>()

    // Funktion zum Generieren einer Quizfrage für die Flagge eines europäischen Landes
   /* fun generateQuizQuestion(): QuizQuestion? {
        if (quizQuestions.isEmpty()) {
            // Wenn alle Fragen beantwortet wurden, ist das Quiz beendet
            return null
        }

        // Wähle zufällig eine Frage aus der Liste
        val randomIndex = (0 until quizQuestions.size).random()
        val quizQuestion = quizQuestions[randomIndex]

        // Entferne die ausgewählte Frage aus der Liste
        quizQuestions.removeAt(randomIndex)

        return quizQuestion
    }*/

    fun continentsList() {
        viewModelScope.launch { repository.getContinents() }
    }

    fun insertQuiz(quizResult: QuizResult) {
        viewModelScope.launch { repository.insertQuizResult(quizResult) }
    }

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