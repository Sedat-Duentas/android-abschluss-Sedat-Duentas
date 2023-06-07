package com.example.laenderapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Für Notizen siehe HomeFragment
class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    private val viewModel: MainViewModel by activityViewModels()

    /*private var countryList = listOf<Country>()

    private var currentIndex = 0*/

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // argumente hier rausholen
        arguments?.let {
            val continentId = it.getInt("continentNameRessourceId")
            val resourceName = resources.getResourceName(continentId)
            val continentName = resourceName.substringAfterLast("/")
            Log.d("continentDebug",continentName)
            viewModel.selectContinent(continentName)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.startQuiz()

        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToContinentsFragment())
        }

        binding.mcQuizCityHome.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToHomeFragment())
        }

        // Neuer Code
        viewModel.currentIndex.observe(viewLifecycleOwner) { index ->
            val country = viewModel.getCurrentCountry
            // Aktualisiere die UI mit dem aktuellen Land
            Log.d("countryDebug", "$country")
            val url =
                "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/${country.flag}"
            binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())

        }

        // Neuer Code
        viewModel.currentOptions.observe(viewLifecycleOwner) { options ->
            // Aktualisiere die UI mit den Quiz-Optionen
            // liste von buttons
            // shuffel der liste
            //

            /*binding.btn1Quiz.text = options.[0]
            binding.btn2Quiz.text = options[1]
            binding.btn3Quiz.text = options[2]
            binding.btn4Quiz.text = options[3]*/
        }
    }

    /*// Neuer Code
    binding.btn1Quiz.setOnClickListener { view ->
        val btn = view as Button
        val currentCountry = viewModel.currentCountry.value
        if (btn.text == currentCountry?.country) {
            // Richtig beantwortet
            btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)
            // TODO: QuizResult in die Datenbank einfügen
        } else {
            // Falsch beantwortet
            btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
        }

        lifecycleScope.launch {
            delay(1500)
            viewModel.showNextCountry()
        }
    }

    // Neuer Code
    viewModel.currentIndex.observe(viewLifecycleOwner) { currentIndex ->
        viewModel.currentCountryList.value?.let { countryList ->
            if (currentIndex < countryList.size - 1) {
                viewModel.generateQuestions()
            } else {
                // Quiz beendet, handle entsprechend
            }
        }
    }






    //viewModel.selectContinent("asia")

    viewModel.selectedCountriesLiveData.observe(
        viewLifecycleOwner
    ) {
        countryList = it.shuffled()

        showCountry()

        val onClick: (View) -> Unit = { view ->
            val btn = view as Button
            val currentCountry = countryList[currentIndex]
            if (btn.text == currentCountry.country) {
                val quizResult: QuizResult =
                    QuizResult(currentCountry.continent, currentCountry.country, true)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.green)

                viewModel.insertQuiz(quizResult)

            } else {
                val quizResult: QuizResult =
                    QuizResult(currentCountry.continent, currentCountry.country, false)
                btn.backgroundTintList =
                    ContextCompat.getColorStateList(requireContext(), R.color.red)
            }
            // TODO QuizResult in die Datenbank

            lifecycleScope.launch {
                delay(1500)
                currentIndex++
                showCountry()
            }

            *//*currentIndex++
                showCountry()*//*
            }

            binding.btn1Quiz.setOnClickListener(onClick)
            binding.btn2Quiz.setOnClickListener(onClick)
            binding.btn3Quiz.setOnClickListener(onClick)
            binding.btn4Quiz.setOnClickListener(onClick)
        }
*/

    /*viewModel.currentCountry.observe(viewLifecycleOwner) { country ->
        // Aktualisiere die UI mit dem aktuellen Land
        val url = "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/${country.flag}"
        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())
    }

    viewModel.quizOptions.observe(viewLifecycleOwner) { options ->
        // Aktualisiere die UI mit den Quiz-Optionen
        binding.btn1Quiz.text = options[0]
        binding.btn2Quiz.text = options[1]
        binding.btn3Quiz.text = options[2]
        binding.btn4Quiz.text = options[3]
    }

    binding.btn1Quiz.setOnClickListener { view ->
        val btn = view as Button
        val currentCountry = viewModel.currentCountry.value
        if (btn.text == currentCountry?.country) {
            // Richtig beantwortet
            btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)
            // TODO: QuizResult in die Datenbank einfügen
        } else {
            // Falsch beantwortet
            btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
        }

        lifecycleScope.launch {
            delay(1500)
            viewModel.showNextCountry()
        }
    }
*/


}
/*
    fun generateRandomOptions(correctOption: String): List<String> {
        val options = mutableListOf<String>()
        options.add(correctOption)

        // Zufällige Auswahl von drei weiteren Ländern aus der countryList
        val shuffledList = countryList.shuffled()
        val randomOptions = shuffledList.subList(0, 3)
        randomOptions.forEach { country ->
            options.add(country.country)
        }

        // Die Optionen werden zufällig sortiert
        options.shuffle()

        return options
    }

    fun showCountry() {
        val currentCountry = countryList[currentIndex]
        val url =
            "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/" + currentCountry.flag
        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())

        val options = generateRandomOptions(currentCountry.country)
        binding.btn1Quiz.text = options[0]
        binding.btn2Quiz.text = options[1]
        binding.btn3Quiz.text = options[2]
        binding.btn4Quiz.text = options[3]
        binding.btn1Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn2Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn3Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn4Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        // randfälle beachten(selbe antwort),
        // doppeltklick, wenn fertig ist stürtzt ein, bei fehler darf es nicht weiter gehen
    }
}*/

