package com.example.laenderapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Country
import com.example.laenderapp.data.datamodels.QuizResult
import com.example.laenderapp.databinding.FragmentQuizBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CurrencyQuizFragment: Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: MainViewModel by activityViewModels()

    private var countryList = listOf<Country>()
    private var currentIndex = 0
    private var progress = 1
    private var totalQuestions = 0
    private var continentForResult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val continentId = it.getInt("continentNameRessourceId")
            val resourceName = resources.getResourceName(continentId)
            val continentName = resourceName.substringAfterLast("/")
            Log.d("continentDebug", continentName)
            viewModel.selectContinent(continentName)
            continentForResult = resources.getString(continentId)
        }
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
            findNavController().navigateUp()
        }

        binding.mcQuizCityHome.setOnClickListener {
            findNavController().navigate(CurrencyQuizFragmentDirections.actionCurrencyQuizFragmentToHomeFragment())
        }

        viewModel.selectedCountriesLiveData.observe(
            viewLifecycleOwner
        ) {
            countryList = it.shuffled()
            totalQuestions = countryList.size
            showCountry()

            val onClick: (View) -> Unit = { view ->
                val btn = view as Button
                val currentCountry = countryList[currentIndex]

                if (btn.text == currentCountry.currency) {
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country, true)
                    btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)
                    viewModel.insertQuiz(quizResult)
                    progress++
                } else {
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country, false)
                    btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
                }

                lifecycleScope.launch {
                    delay(500)
                    currentIndex++
                    showCountry()
                }
            }

            binding.btn1Quiz.setOnClickListener(onClick)
            binding.btn2Quiz.setOnClickListener(onClick)
            binding.btn3Quiz.setOnClickListener(onClick)
            binding.btn4Quiz.setOnClickListener(onClick)
        }
    }

    fun generateRandomOptions(correctOption: String): List<String> {
        val options = mutableListOf<String>()
        options.add(correctOption)
        while (options.size <= 3) {
            val shuffledList = countryList.shuffled()
            val randomOptions = shuffledList.subList(0, 3)
            randomOptions.forEach { country ->
                if (country.currency != correctOption) {
                    options.add(country.currency)
                } else {
                    // Aktionen, wenn die Währung mit der korrekten Option übereinstimmt
                }
            }
        }
        options.shuffle() // Optionen werden zufällig sortiert
        return options
    }

    fun showCountry() {
        val currentCountry = countryList[currentIndex]
        val url = "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/" + currentCountry.flag
        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())
        binding.tvProgress.text = "${currentIndex + 1}/$totalQuestions"
        val options = generateRandomOptions(currentCountry.currency)
        binding.btn1Quiz.text = options[0]
        binding.btn2Quiz.text = options[1]
        binding.btn3Quiz.text = options[2]
        binding.btn4Quiz.text = options[3]
        binding.btn1Quiz.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn2Quiz.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn3Quiz.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn4Quiz.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange)

        if (currentIndex == countryList.size - 1) {
            val action = CurrencyQuizFragmentDirections.actionCurrencyQuizFragmentToResultFragment(progress, continentForResult)
            findNavController().navigate(action)
        }
    }
}