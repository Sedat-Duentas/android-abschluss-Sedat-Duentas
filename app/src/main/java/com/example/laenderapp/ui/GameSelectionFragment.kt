package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.laenderapp.R
import com.example.laenderapp.databinding.FragmentGameSelectionBinding

class GameSelectionFragment : Fragment() {

    // Die Instanz der generierten Binding-Klasse, die Zugriff auf die Views des Fragments bietet.
    private lateinit var binding: FragmentGameSelectionBinding

    // Die Instanz des MainViewModel, welches Zugriff auf die Daten und Funktionen des ViewModels bietet.
    private val viewModel: MainViewModel by activityViewModels()

    // In der onCreateView wird das layout grob erstellt, container, größe etc
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_selection, container, false)
        return binding.root
    }

    // Wir benutzen den navcontroller um unsere navigation aus dem nav_graph durchzuführen
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.selectionCardViewFlags.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment("flag"))
        }

        binding.selectionCardViewCity.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment("city"))
        }

        binding.selectionCardViewCurrency.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment("currency"))
        }

        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToHomeFragment())
        }
    }
}