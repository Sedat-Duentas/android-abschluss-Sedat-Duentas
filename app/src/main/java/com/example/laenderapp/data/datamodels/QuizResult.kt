package com.example.laenderapp.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizResult(
    @PrimaryKey
    val country: String,
    val continent: String,
    val correct: Boolean
)