package com.example.laenderapp.data.remote

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.QuizResult
import com.example.laenderapp.data.local.ContinentsDatabase
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception
import kotlin.math.log

const val TAG = "AppRepository"

class AppRepository(private val api: CountryApi, private val database: ContinentsDatabase) {
// Hier sind die LiveData Daten die verwendet werden um die einzelnen Länder der Kontinente zu laden
    val europeCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("europe")
    val asiaCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("asia")
    val africaCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("africa")
    val northAmericaCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("northAmerica")
    val southAmericaCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("southAmerica")
    val oceaniaCountries: LiveData<List<Country>> =
        database.continentsDatabaseDao.getAllCountriesByContinent("oceania")

    suspend fun getContinents() {
        withContext(Dispatchers.IO) {
            try {
                // Hier wird das Ergebnis der API in einer Variable gespeichert
                val result: Continents = api.retrofitService.loadContinents()
                database.continentsDatabaseDao.deleteCountries()

                // Hier werden die Ergebnise der API gemapt auf die verschiedenen Kontinente
                database.continentsDatabaseDao.insertCountries(result.europe.map { it.copy(continent = "europe") })
                database.continentsDatabaseDao.insertCountries(result.asia.map { it.copy(continent = "asia") })
                database.continentsDatabaseDao.insertCountries(result.africa.map { it.copy(continent = "africa") })
                database.continentsDatabaseDao.insertCountries(result.northAmerica.map { it.copy(continent = "northAmerica") })
                database.continentsDatabaseDao.insertCountries(result.southAmerica.map { it.copy(continent = "southAmerica") })
                database.continentsDatabaseDao.insertCountries(result.oceania.map { it.copy(continent = "oceania") })

            } catch (e: Exception) {
                Log.d(TAG, "${e.toString()}")
            }
        }
    }

    suspend fun insertQuizResult(quizResult: QuizResult) {
        database.continentsDatabaseDao.insertQuizResult(quizResult)
    }
}

