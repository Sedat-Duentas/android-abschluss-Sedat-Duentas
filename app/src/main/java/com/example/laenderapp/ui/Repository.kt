package com.example.laenderapp.ui

import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Continent

// Hier wird aus der Continents Klasse eine funktion erstellt,
// die eine Liste aus den einzelnen Kontinenten erstellt und diese zurückgibt.
// Hierzu werden die einzelnen Objekte mit den werten initialisiert, die in den Ressourcen sind.
class Repository {

    fun loadContinents() : List<Continent> {
        val continents = listOf(
            Continent(R.string.europe, R.drawable.europe),
            Continent(R.string.asia, R.drawable.asia),
            Continent(R.string.africa, R.drawable.africa),
            Continent(R.string.northamerica, R.drawable.nordamerika),
            Continent(R.string.suedamerica, R.drawable.suedamerika),
            Continent(R.string.ozeanien, R.drawable.ozeanien),
        )
        return continents
    }
}