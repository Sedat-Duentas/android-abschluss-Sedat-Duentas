package com.example.laenderapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.CountryEurope
import com.example.laenderapp.data.datamodels.QuizResult

@Dao
interface ContinentsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(continents: List<Country>)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizResult(quizResult: QuizResult)

    @Query("SELECT * from Country WHERE continent = :continent")
    fun getAllCountriesByContinent(continent: String): LiveData<List<Country>>

    @Query("DELETE from Country")
    fun deleteCountries()

}