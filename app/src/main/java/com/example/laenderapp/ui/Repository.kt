package com.example.laenderapp.ui

import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Adaptermodel

// Hier wird aus der Continents Klasse eine funktion erstellt,
// die eine Liste aus den einzelnen Kontinenten erstellt und diese zurückgibt.
// Hierzu werden die einzelnen Objekte mit den werten initialisiert, die in den Ressourcen sind.
class Repository {

    fun loadContinents() : List<Adaptermodel> {
        val adaptermodels = listOf(
            Adaptermodel(R.string.europe, R.drawable.europe),
            Adaptermodel(R.string.asia, R.drawable.asia),
            Adaptermodel(R.string.africa, R.drawable.africa),
            Adaptermodel(R.string.northAmerica, R.drawable.nordamerika),
            Adaptermodel(R.string.southAmerica, R.drawable.suedamerika),
            Adaptermodel(R.string.oceania, R.drawable.ozeanien),
        )
        return adaptermodels
    }
}