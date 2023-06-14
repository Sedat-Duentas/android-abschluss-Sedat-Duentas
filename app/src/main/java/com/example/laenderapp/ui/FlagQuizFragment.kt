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

// Für Notizen siehe HomeFragment
class FlagQuizFragment : Fragment() {

    // Die Instanz der generierten Binding-Klasse, die Zugriff auf die Views des Fragments bietet.
    private lateinit var binding: FragmentQuizBinding

    // Die Instanz des MainViewModel, welches Zugriff auf die Daten und Funktionen des ViewModels bietet.
    private val viewModel: MainViewModel by activityViewModels()

    // Diese Variable wird verwendet, um eine Liste von Ländern zu speichern.
    // Der Typ List<Country> gibt an, dass es sich um eine Liste von Objekten vom Typ Country handelt.
    // Hier ist die Liste leer, da sie mit listOf() initialisiert wurde.
    private var countryList = listOf<Country>()

    // Diese Variable wird verwendet, um den Index des aktuellen Landes in der countryList zu verfolgen.
    // Hier ist der Index auf 0 gesetzt, was bedeutet, dass das erste Land in der Liste ausgewählt ist.
    private var currentIndex = 0

    // Diese Variable wird verwendet, um den Fortschritt des Quiz zu verfolgen.
    // Hier ist der Fortschritt auf 1 gesetzt, was bedeutet, dass der Benutzer gerade die erste Frage beantwortet.
    private var progress = 1

    // Diese Variable wird verwendet, um die Gesamtzahl der Fragen im Quiz zu speichern.
    // Hier ist die Anzahl der Fragen auf 0 gesetzt, was darauf hindeutet, dass es keine Fragen gibt.
    private var totalQuestions = 0

    // Diese Variable wird verwendet, um den Kontinent zu speichern,
    // für den das Quiz durchgeführt wird und der später im Ergebnis angezeigt werden soll.
    // Derzeit ist der Kontinent leer.
    private var continentForResult = ""

    // In der onCreate-Methode wird das Fragment vorbereitet und die übergebenen Argumente angenommen.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            // Ressourcen-ID des Kontinents aus den übergebenen Argumenten erhalten
            val continentId = it.getInt("continentNameRessourceId")

            // Den Namen der Ressource anhand der Ressourcen-ID erhalten
            val resourceName = resources.getResourceName(continentId)

            // Den Kontinentnamen aus der Ressourcen-Namen-Zeichenkette extrahieren
            val continentName = resourceName.substringAfterLast("/")

            // Debug-Log, um den extrahierten Kontinentnamen anzuzeigen
            Log.d("continentDebug", continentName)

            // Den ausgewählten Kontinent im ViewModel setzen
            viewModel.selectContinent(continentName)

            // Den Kontinentnamen für das ResultFragment speichern
            continentForResult = resources.getString(continentId)
        }
    }

    //In der onCreateView wird das layout grob erstellt, container, größe etc
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }
    // In der onViewCreated greifen wir auf das layout zu und befüllen es mit den korrekten Daten
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Navigation vom QuizFragment zum ContinentsFragment
        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigateUp()
            //(FlagQuizFragmentDirections.actionQuizFragmentToContinentsFragment())
        }

        // Navigation vom QuizFragment zum HomeFragment
        binding.mcQuizCityHome.setOnClickListener {
            findNavController().navigate(FlagQuizFragmentDirections.actionQuizFragmentToHomeFragment())

        }

        // Hier wird die selectedCountriesLiveData beobachtet
        viewModel.selectedCountriesLiveData.observe(
            viewLifecycleOwner
        ) {
            // // Die Liste der ausgewählten Länder wird gemischt und zugewiesen
            countryList = it.shuffled()

            // Die Gesamtanzahl der Fragen wird auf die Größe der Länderliste gesetzt
            totalQuestions = countryList.size

            // Die Methode showCountry() wird aufgerufen, um das erste Land anzuzeigen
            showCountry()

            val onClick: (View) -> Unit = { view -> // Eine Variable onClick wird deklariert, die ein Lambda-Ausdruck ist. Der Lambda-Ausdruck hat einen Eingabeparameter view vom Typ View und keine Rückgabewerte (Unit).
                val btn = view as Button // Die View wird als Button referenziert und in der Variable btn gespeichert.
                val currentCountry = countryList[currentIndex] // Das aktuelle Land basierend auf dem aktuellen Index aus der countryList

                // Überprüfung, ob die ausgewählte Option mit dem aktuellen Land übereinstimmt
                if (btn.text == currentCountry.country) {

                    // Erzeugung eines QuizResult-Objekts mit den relevanten Informationen
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country, true)

                    // Änderung der Hintergrundfarbe des Buttons auf grün
                    btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)

                    // Einfügen des QuizResult-Objekts in die Datenbank über das ViewModel
                    viewModel.insertQuiz(quizResult)

                    // Erhöhung des Fortschritts
                    progress++

                } else {
                    // Erzeugung eines QuizResult-Objekts mit den relevanten Informationen
                    val quizResult: QuizResult = QuizResult(currentCountry.continent, currentCountry.country, false)

                    // Änderung der Hintergrundfarbe des Buttons auf rot
                    btn.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
                }

                // Verzögerung vor dem Anzeigen der nächsten Frage
                lifecycleScope.launch {
                    delay(500)

                    // Erhöhung des aktuellen Index
                    currentIndex++

                    // Aufruf der Methode showCountry(), um die nächste Frage anzuzeigen
                    showCountry()
                }
            }

            // Durch den Aufruf der setOnClickListener-Methode mit dem Parameter onClick wird der zuvor definierte Klickereignis-Handler onClick den Buttons zugewiesen.
            binding.btn1Quiz.setOnClickListener(onClick)
            binding.btn2Quiz.setOnClickListener(onClick)
            binding.btn3Quiz.setOnClickListener(onClick)
            binding.btn4Quiz.setOnClickListener(onClick)
        }
    }

    // Eine Funktion, um zufällige Antwortoptionen zu generieren
    fun generateRandomOptions(correctOption: String): List<String> {

        // Eine leere Liste für die Antwortoptionen
        val options = mutableListOf<String>()

        // Die korrekte Option wird zur Liste hinzugefügt
        options.add(correctOption)

        // Zufällige Auswahl von drei weiteren Ländern aus der countryList
        while (options.size <= 3) {

            // Die countryList wird zufällig durcheinandergemischt
            val shuffledList = countryList.shuffled()

            // Die ersten drei Elemente der gemischten Liste werden ausgewählt
            val randomOptions = shuffledList.subList(0, 3)

            // Für jedes Land in den zufälligen Optionen
            // Eine Schleife, um weitere zufällige Optionen auszuwählen, falls die Anzahl der Optionen kleiner oder gleich 3 ist.
            randomOptions.forEach { country ->
                // Dann wird für jedes Land in den zufälligen Optionen überprüft, ob es nicht mit der korrekten Option übereinstimmt.
                if (country.country != correctOption) {
                    // Wenn es nicht übereinstimmt, wird es zur Liste der Optionen hinzugefügt
                    options.add(country.country)
                } else {
                    // Hier könnten zusätzliche Aktionen ausgeführt werden, wenn das Land mit der korrekten Option übereinstimmt
                }
            }
        }

        // Die Optionen werden zufällig sortiert
        options.shuffle()

        // Gib die generierten Antwortoptionen zurück
        return options
    }

    // Funktion zum Anzeigen des aktuellen Landes in der Quiz-Frage
    fun showCountry() {

        // Aktuelles Land aus der countryList abrufen
        val currentCountry = countryList[currentIndex]

        // URL für die Flagge des aktuellen Landes erstellen
        val url = "https://public.syntax-institut.de/apps/batch6/SedatDuentas/images/" + currentCountry.flag

        // Die Flagge wird mit Hilfe von Coil in das ImageView geladen
        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())

        // Aktualisierung der Zahl
        binding.tvProgress.text = "${currentIndex + 1}/$totalQuestions"

        // Generiert zufällige Antwortoptionen für das aktuelle Land
        val options = generateRandomOptions(currentCountry.country)

        // Setzt die Texte der Antwortbuttons auf die generierten Optionen
        binding.btn1Quiz.text = options[0]
        binding.btn2Quiz.text = options[1]
        binding.btn3Quiz.text = options[2]
        binding.btn4Quiz.text = options[3]

        // Setzt die Hintergrundfarbe der Antwortbuttons wieder auf Orange
        binding.btn1Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn2Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn3Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)
        binding.btn4Quiz.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.orange)

        // Überprüft, ob alle Fragen beantwortet wurden
        if (currentIndex == countryList.size - 1) {

            // Navigiert zum ResultFragment, wenn alle Fragen beantwortet wurden
            val action = FlagQuizFragmentDirections.actionQuizFragmentToResultFragment(progress, continentForResult)
            findNavController().navigate(action)
        }
    }
}

