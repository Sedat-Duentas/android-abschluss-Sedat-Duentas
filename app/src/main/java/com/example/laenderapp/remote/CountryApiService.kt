package com.example.laenderapp.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.iplocate.com/ip/«ip_address»/key/«api_key»/output/«format»/timezone/«bool»/hostname/«bool»/language/«bool»/currency/«bool»"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryApiService {
    @GET("")
    suspend fun loadflags(): Flaglist
}

object DrinkApi {
    val retrofitService: CountryApiService by lazy { retrofit.create(CountryApiService::class.java) }
}