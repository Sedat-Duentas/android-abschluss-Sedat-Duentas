package com.example.laenderapp.ui

import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Continent

class Repository {

    fun loadContinents() : List<Continent> {
        val continents = listOf(
            Continent(R.string.europe, R.drawable.europe),
            Continent(R.string.asia, R.drawable.asia),
            Continent(R.string.africa, R.drawable.africa),
            Continent(R.string.america, R.drawable.america),
            Continent(R.string.australia, R.drawable.australia),
        )
        return continents
    }
}