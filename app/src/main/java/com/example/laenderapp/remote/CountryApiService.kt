package com.example.laenderapp.remote

const val BASE_URL = "https://api.iplocate.com/ip/«ip_address»/key/«api_key»/output/«format»/timezone/«bool»/hostname/«bool»/language/«bool»/currency/«bool»"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountryApiService {
    @GET()
    suspend fun
}
