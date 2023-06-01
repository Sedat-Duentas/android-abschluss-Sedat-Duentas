package com.example.laenderapp.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var continent: String = "",
    val country: String,
    val capital: String,
    val language: String,
    val flag: String,
    val currency: String
)