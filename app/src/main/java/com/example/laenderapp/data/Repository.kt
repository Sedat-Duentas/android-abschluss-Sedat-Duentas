package com.example.laenderapp.data

import com.example.laenderapp.R
import com.example.laenderapp.data.model.Continent

class Repository {

    fun loadContinents() {
        val continents = listOf(
            Continent(R.drawable.austria),
            Continent(R.drawable.belgien),
            Continent(R.drawable.bulgarien)
        )
    }
}