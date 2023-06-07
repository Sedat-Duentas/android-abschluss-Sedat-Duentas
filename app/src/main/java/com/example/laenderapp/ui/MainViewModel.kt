package com.example.laenderapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.QuizQuestion
import com.example.laenderapp.data.datamodels.QuizResult
import com.example.laenderapp.data.local.getDatabase
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository(CountryApi, getDatabase(context = application))

    // LiveData-Variable, um die Liste der Optionen für das aktuelle Quiz-Land zu erhalten
    // Sie verändert sich wenn das nächste Land ausgewählt wurde
    private val _currentOptions = MutableLiveData<QuizQuestion>()
    val currentOptions: LiveData<QuizQuestion> = _currentOptions

    // Eine LiveData die, die aktuelle Stelle eines Landes im currentCountryList beinhaltet
    // Sie verändert sich wenn das nächste Land angezeigt werden soll (nach QuizFrage)
    private var _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int>
        get() = _currentIndex

    // Eine LiveData die alle Länder des aktuellen Kontinent beinhaltet
    // sie verändert sicht wenn ein Kontinent ausgewählt wird
    // verursacht initialisierung vom Quiz
    private var _currentCountryList = MutableLiveData<List<Country>>()
    val currentCountryList: LiveData<List<Country>>
        get() = _currentCountryList

    val getCurrentCountry: Country
        get() = currentIndex.value?.let { currentCountryList.value?.get(it) }!!

    init {
        continentsList()
    }

    fun processAnswer(answer: String) {

    }

    // Hier werden die Daten vom Repository in eine LiveData geladen
    val europeCountriesLiveData = repository.europeCountries
    val asiaCountriesLiveData = repository.asiaCountries
    val africaCountriesLiveData = repository.africaCountriesCountries
    val northAmericaCountriesLiveData = repository.northAmericaCountries
    val southAmericaCountriesLiveData = repository.southAmericaCountries
    val oceaniaCountriesLiveData = repository.oceaniaCountries


    // Hier wird die Funktion getContinents() von der Repository in einem viewModelScope.launch gepackt
    fun continentsList() {
        viewModelScope.launch { repository.getContinents() }
    }

    // Hier wird die Funktion insertQuizResult() von der Repository in einem viewModelScope.launch gepackt
    fun insertQuiz(quizResult: QuizResult) {
        viewModelScope.launch { repository.insertQuizResult(quizResult) }
    }

    // Hier wird die Optionsauswahl von den Kontinenten bestimmt
    fun selectContinent(continent: String) {
        var countries = listOf<Country>()
        Log.d("liveData", "$europeCountriesLiveData")
        Log.d("liveData", "${europeCountriesLiveData.value}")
        when (continent) {
            "europe" -> countries = europeCountriesLiveData.value!!
            "asia" -> countries = asiaCountriesLiveData.value!!
            "africa" -> countries = africaCountriesLiveData.value!!
            "northAmerica" -> countries = northAmericaCountriesLiveData.value!!
            "southAmerica" -> countries = southAmericaCountriesLiveData.value!!
            "oceania" -> countries = oceaniaCountriesLiveData.value!!

            else -> Log.e(TAG, "Auswahl des Kontinents: $continent fehlgeschlagen")
        }
        _currentCountryList.value = countries
    }

    fun startQuiz() {
        _currentIndex.value = 0
        generateQuestions()
    }

    // Bei generateQuestions muss der Index schon gesetzt sein, generiert die Fragen für das Land
    fun generateQuestions() {
        val shuffledList = currentCountryList.value?.shuffled()
        val randomOptions = shuffledList?.filter { it.country != getCurrentCountry.country }
            ?.shuffled()
            ?.subList(0, 3)

        val quiz: QuizQuestion = QuizQuestion(
            getCurrentCountry.country,
            randomOptions!![0].country,
            randomOptions!![1].country,
            randomOptions!![2].country
        )

        _currentOptions.value = quiz
    }

    fun showNextCountry() {
        val currentIndex = _currentIndex.value ?: 0
        val countryList = _currentCountryList.value ?: emptyList()

        if (currentIndex < countryList.size - 1) {
            _currentIndex.value = currentIndex + 1
            generateQuestions()
        } else {
            // Quiz beendet, handle entsprechend
        }
    }
}


/*fun showNextCountry() {
    val currentCountryList = selectedCountriesLiveData.value
    if (currentIndex < countryList.size) {
        _currentCountry.value = countryList[currentIndex]
        _currentOptions.value = generateRandomOptions(countryList[currentIndex].country)
        currentIndex++
    } else {
        // Quiz beendet, handle entsprechend
    }
}

fun getNextQuizOptions(): List<String> {
    val currentCountry = countryList[currentIndex]
    return generateRandomOptions(currentCountry.country)
}*/

// private val quizQuestions = mutableListOf<QuizQuestion>()

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

/*
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


  // Hier wird erstmal die europäische LiveData ins selectedCountriesLiveData,
    // was später in der Funktion selectContinent() je nach Kontinent neu geladen wird
    var selectedCountriesLiveData = europeCountriesLiveData

}*/
