package com.example.laenderapp.remotes

import com.example.laenderapp.data.datamodels.AllCountryCodes
import com.example.laenderapp.data.datamodels.CountryCodes
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://flagcdn.com/en/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryApiService {
    @GET("codes.json")
    suspend fun loadCountryCodes(): AllCountryCodes
}

object CountryApi {
    val retrofitService: CountryApiService by lazy { retrofit.create(CountryApiService::class.java) }
}