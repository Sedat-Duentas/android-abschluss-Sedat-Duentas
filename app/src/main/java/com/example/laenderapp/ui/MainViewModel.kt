package com.example.laenderapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.QuizQuestion
import com.example.laenderapp.data.local.getDatabase
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(CountryApi, getDatabase(context = application))


    val europeCountriesLiveData = repository.europeCountries
    val asiaCountriesLiveData = repository.asiaCountries
    val africaCountriesLiveData = repository.africaCountriesCountries
    val northAmericaCountriesLiveData = repository.northAmericaCountries
    val southAmericaCountriesLiveData = repository.southAmericaCountries
    val oceaniaCountriesLiveData = repository.oceaniaCountries

    private val quizQuestions = mutableListOf<QuizQuestion>()

    // Funktion zum Generieren einer Quizfrage für die Flagge eines europäischen Landes
    fun generateQuizQuestion(): QuizQuestion? {
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
    }

    // Funktion zum Initialisieren der Quizfragen mit Flaggen europäischer Länder
    /*fun initializeQuizQuestions() {
        europeCountriesLiveData.value?.let { europeCountries ->
            // Füge für jedes europäische Land eine Quizfrage hinzu
            quizQuestions.clear()
            for (country in europeCountries) {
                // Erstelle eine Quizfrage mit der Flagge des Landes
                val quizQuestion = QuizQuestion(country.flag, listOf(country.country))
                quizQuestions.add(quizQuestion)
            }
        }
    }
    // ...*/

    fun continentsList() {
        viewModelScope.launch { repository.getContinents() }
    }
}