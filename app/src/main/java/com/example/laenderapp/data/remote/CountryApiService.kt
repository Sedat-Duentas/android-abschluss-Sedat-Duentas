package com.example.laenderapp.remotes

import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.data.datamodels.Country
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/SedatDuentas/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryApiService {
    @GET("data.json")
    suspend fun loadContinents(): Continents
}

object CountryApi {
    val retrofitService: CountryApiService by lazy { retrofit.create(CountryApiService::class.java) }
}