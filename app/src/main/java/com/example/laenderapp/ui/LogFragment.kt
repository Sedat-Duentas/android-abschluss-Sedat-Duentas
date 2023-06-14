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
import com.example.laenderapp.databinding.FragmentLogBinding

class LogFragment : Fragment() {

    // Wir nutzen binding für unser fragment und deklarieren eine leere variable vom Typ FragmentContinentsBinding
    private lateinit var binding: FragmentLogBinding

    // Die Instanz des MainViewModel, welches Zugriff auf die Daten und Funktionen des ViewModels bietet.
    private val viewModel: MainViewModel by activityViewModels()

    // In der onCreate-Methode wird das Fragment vorbereitet und die übergebenen Argumente angenommen.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log, container, false)
        return binding.root
    }

    // Die onViewCreated-Methode wird aufgerufen, nachdem die View des Fragments erstellt wurde.
    // Hier werden die Klick-Handler und die Navigation initialisiert.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logBtn.setOnClickListener {

            // Beim Klicken auf den Button wird der eingegebene Benutzername aus dem textInputEditText-Feld abgerufen
            val userName = binding.textInputEditText.text.toString()

            // Und über das viewModel-Objekt gesetzt.
            viewModel.setUserName(userName)

            // Navigation vom LogFragment zu HomeFragment
            findNavController().navigate(LogFragmentDirections.actionLogFragmentToHomeFragment())
        }
    }
}