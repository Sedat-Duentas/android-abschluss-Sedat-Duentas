package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.QuizQuestion
import com.example.laenderapp.data.datamodels.QuizResult
import com.example.laenderapp.data.local.getDatabase
import com.example.laenderapp.data.remote.AppRepository
import com.example.laenderapp.databinding.FragmentQuizBinding
import com.example.laenderapp.remotes.CountryApi
import kotlinx.coroutines.launch

// Für Notizen siehe HomeFragment
class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    private val viewModel: MainViewModel by activityViewModels()

    private val repository = AppRepository(CountryApi, getDatabase(requireContext()))

    private var laenderListe = listOf<Country>()

    private var currentIndex = 0

    private val currentCountry: Country
        get() = laenderListe[currentIndex]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        laenderListe = viewModel.europeCountriesLiveData.value!!.shuffled()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToContinentsFragment())
        }

        binding.mcQuizCityHome.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToHomeFragment())
        }

        showCountry()

        val onClick: (View) -> Unit = {
                val btn = it as Button

                if (btn.text == currentCountry.country) {
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country,true)
                    btn.setBackgroundResource(R.color.green)

                    insertQuizResult(quizResult)


                } else {
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country,false)
                    btn.setBackgroundResource(R.color.red)
                }
                // TODO QuizResult in die Datenbank

                currentIndex++
                showCountry()

        }

        binding.btn1Quiz.setOnClickListener(onClick)
        binding.btn2Quiz.setOnClickListener(onClick)
        binding.btn3Quiz.setOnClickListener(onClick)
        binding.btn4Quiz.setOnClickListener(onClick)


        //TODO hier kommen die Knöpfe wie unten, random aber auch überprüfen das nicht das koreekte land mitgenommen wird
        //TODO überprüfen welcher knopf gedrückt wurde, also text von curentcountry ob der richtig ist
        //TODO wenn ja dann wird QuizResult auf true gesetzt andernfalls auf false
        //TODO background wird gesetzt, je nach klick

    }
    fun generateRandomOptions(correctOption: String): List<String> {
        val options = mutableListOf<String>()
        options.add(correctOption)

        // Zufällige Auswahl von drei weiteren Ländern aus der countryList
        val shuffledList = laenderListe.shuffled()
        val randomOptions = shuffledList.subList(0, 3)
        randomOptions.forEach { country ->
            options.add(country.country)
        }

        // Die Optionen werden zufällig sortiert
        options.shuffle()

        return options
    }

    fun showCountry() {
        val url =
            "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/" + currentCountry.flag
        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())

        val options = generateRandomOptions(currentCountry.country)
        binding.btn1Quiz.text = options[0]
        binding.btn2Quiz.text = options[1]
        binding.btn3Quiz.text = options[2]
        binding.btn4Quiz.text = options[3]


    }

    fun insertQuizResult(quizResult: QuizResult) {
        viewModel.viewModelScope.launch {
            repository.insertQuizResult(quizResult)
        }
    }


}


/*
        fun checkAnswer(quizQuestion: QuizResult, selectedOption: String) {
            val correctOption = quizQuestion.correct

            if (selectedOption == correct) {
                binding.btn3Quiz.setBackgroundResource(R.color.green)
                // Progressbar erhöhen
                //nextFlag()
            } else {
                binding.btn3Quiz.setBackgroundResource(R.color.red)
            }
        }

        fun nextFlag() {

        }

        var btn = listOf(binding.btn1Quiz, binding.btn2Quiz, binding.btn3Quiz, binding.btn4Quiz)

            if (btn == correct) {
                var randomBtn = btn.random()

                randomBtn.text = "Deutschland"*/

