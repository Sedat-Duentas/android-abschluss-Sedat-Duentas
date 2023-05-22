package com.example.laenderapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.laenderapp.data.datamodels.CountryEurope

@Dao
interface ContinentsDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEuropeanCountries(continents: List<CountryEurope>)

    /*@Query("SELECT * from CountryEurope")
    fun getAllEuropeanCountries() : LiveData<List<CountryEurope>>*/

}