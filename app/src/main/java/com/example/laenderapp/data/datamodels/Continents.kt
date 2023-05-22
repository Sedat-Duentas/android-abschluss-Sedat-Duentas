package com.example.laenderapp.data.datamodels

data class Continents(
        val europe: List<Country>,
        val asia: List<Country>,
        val africa: List<Country>,
        val northAmerica: List<Country>,
        val southAmerica: List<Country>,
        val oceania: List<Country>
    )