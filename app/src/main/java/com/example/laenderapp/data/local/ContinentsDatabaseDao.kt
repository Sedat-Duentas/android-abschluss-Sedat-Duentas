package com.example.laenderapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.CountryEurope

@Dao
interface ContinentsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(continents: List<Country>)

    @Query("SELECT * from Country")
    fun getAllCountries() : LiveData<List<Country>>

    @Query("SELECT * from Country WHERE continent = :continent")
    fun getAllCountriesByContinent(continent: String): LiveData<List<Country>>

}