package com.example.laenderapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.laenderapp.R
import com.example.laenderapp.databinding.FragmentResultBinding

// Für Notizen siehe HomeFragment
class ResultFragment : Fragment() {

    // Die Instanz der generierten Binding-Klasse, die Zugriff auf die Views des Fragments bietet.
    private lateinit var binding: FragmentResultBinding

    // Die Instanz des MainViewModel, welches Zugriff auf die Daten und Funktionen des ViewModels bietet.
    private val viewModel: MainViewModel by activityViewModels()

    // Die Argumente, die beim Navigieren zu diesem Fragment übergeben wurden. Sie werden durch navArgs() initialisiert und ermöglichen den Zugriff auf die übergebenen Werte.
    private val args: ResultFragmentArgs by navArgs()

    // In der onCreateView wird das layout grob erstellt, container, größe etc
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        return binding.root
    }

    // In der onViewCreated greifen wir auf das layout zu und befüllen es mit den korrekten Daten
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Navigation vom ResulFragment zum HomeFragment
        binding.resultHomeButton.setOnClickListener {
            findNavController().navigate(ResultFragmentDirections.actionResultFragmentToHomeFragment())
        }

        // Die Variablen numFlags und numContinent werden aus den Argumenten extrahiert,
        // und ihre Werte werden im Layout angezeigt, um dem Benutzer das Ergebnis anzuzeigen.
        val numFlags = args.progress
        val numContinent = args.continentName

        // Hier wird der Username vom LogFragment geladen und mit dem Text im tvResultName ersetzt
        binding.tvResultName.text = viewModel.userName + " du hast"

        // Hier wird der Text vom tvResultPoints mit den Argumenten überschrieben
        binding.tvResultPoints.text = numFlags.toString() + " Flaggen von $numContinent erraten!"
    }

}