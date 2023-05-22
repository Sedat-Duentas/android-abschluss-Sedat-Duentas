package com.example.laenderapp.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.local.ContinentsDatabase
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

const val TAG = "AppRepository"
class AppRepository(private val api: CountryApi, private val database: ContinentsDatabase) {

   /* val continents: MutableLiveData<Continents> = database.continentsDatabaseDao.getAllEuropeanCountries()

     suspend fun getContinents () {
         withContext(Dispatchers.IO) {
             try {
                 val result: Continents = api.retrofitService.loadContinents()
                 continents.value = result

             } catch (e: Exception) {
                 Log.d(TAG, "${e.toString()}")
             }

             database.continentsDatabaseDao.insertEuropeanCountries(result.europe.countries)
             database.continentsDatabaseDao.insertEuropeanCountries(result.asia.countries)
             database.continentsDatabaseDao.insertEuropeanCountries(result.africa.countries)
             database.continentsDatabaseDao.insertEuropeanCountries(result.northAmerica.countries)
             database.continentsDatabaseDao.insertEuropeanCountries(result.southAmerica.countries)
             database.continentsDatabaseDao.insertEuropeanCountries(result.oceania.countries)
         }
    }*/
}

