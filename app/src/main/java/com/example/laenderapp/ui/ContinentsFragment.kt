package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.laenderapp.R
import com.example.laenderapp.adapter.ContinetsAdapter
import com.example.laenderapp.databinding.FragmentContinentsBinding

class ContinentsFragment : Fragment() {

    // Wir nutzen binding für unser fragment und deklarieren eine leere variable vom Typ FragmentContinentsBinding
    private lateinit var binding: FragmentContinentsBinding

    private lateinit var gameMode: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            // Ressourcen-ID des Kontinents aus den übergebenen Argumenten erhalten
            gameMode = it.getString("gameMode")!!
        }
    }

    // In der onCreateView wird das layout grob erstellt, container, größe etc
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hier setzten/laden wir auf die leere erstellte variable das richtige Layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_continents, container, false)
        return binding.root
    }

    // In der onViewCreated greifen wir auf das layout zu und befüllen es mit den korrekten Daten
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // RecyclerView vom Layout wird mit dem Code verknüpft
        val recyclerView = binding.rvContinents

        // Hier laden wir die Liste aus der Datasource, in eine Variable
        val continents = Repository().loadContinents()

        // ContinetsAdapter wird als Adapter mit der übergebenen liste und dfestgelegt
        recyclerView.adapter = ContinetsAdapter(continents, gameMode)

        // Verbessert die Performance bei fester größe
        recyclerView.setHasFixedSize(true)

        // Wir benutzen den navcontroller um unsere navigation aus dem nav_graph durchzuführen
        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(ContinentsFragmentDirections.actionContinentsFragmentToGameSelectionFragment())
        }

        binding.mcContinentsHome.setOnClickListener {
            findNavController().navigate(ContinentsFragmentDirections.actionContinentsFragmentToHomeFragment())
        }
    }
}