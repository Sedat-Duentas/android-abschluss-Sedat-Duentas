package com.example.laenderapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laenderapp.data.datamodels.CountryAfrica
import com.example.laenderapp.data.datamodels.CountryAsia
import com.example.laenderapp.data.datamodels.CountryEurope
import com.example.laenderapp.data.datamodels.CountrynorthAmerica
import com.example.laenderapp.data.datamodels.Countryoceania
import com.example.laenderapp.data.datamodels.CountrysouthAmerica

@Database(entities = [
    CountryEurope::class,
    CountryAsia::class,
    CountryAfrica::class,
    CountrynorthAmerica::class,
    CountrysouthAmerica::class,
    Countryoceania::class ], version = 1)
abstract class ContinentsDatabase : RoomDatabase() {

    abstract val continentsDatabaseDao: ContinentsDatabaseDao
}

private lateinit var INSTANCE: ContinentsDatabase

fun getDatabase(context: Context): ContinentsDatabase {
    synchronized(ContinentsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ContinentsDatabase::class.java,
                "continents_database"
            )
                .build()
        }
    }
    return INSTANCE
}