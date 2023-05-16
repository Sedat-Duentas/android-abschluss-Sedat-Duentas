package com.example.laenderapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.laenderapp.data.datamodels.CountryCodes
import com.example.laenderapp.remotes.CountryApi
import com.example.laenderapp.remotes.CountryApiService
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import kotlin.math.log

const val TAG = "AppRepository"
class AppRepository(private val api: CountryApi) {

     suspend fun getCountryCodes () {

         try {
             val flags: List<CountryCodes> = api.retrofitService.loadCountryCodes().countryCodesList

         } catch (e: Exception) {
             Log.d(TAG, "${e.toString()}")
         }
    }
}

