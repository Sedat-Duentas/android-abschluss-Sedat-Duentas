package com.example.laenderapp.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.remotes.CountryApi
import kotlin.Exception

const val TAG = "AppRepository"
class AppRepository(private val api: CountryApi) {

    val continents = MutableLiveData<Continents>()

     suspend fun getContinents () {

         try {
             val result: Continents = api.retrofitService.loadContinents()
             continents.value = result

         } catch (e: Exception) {
             Log.d(TAG, "${e.toString()}")
         }
    }
}

