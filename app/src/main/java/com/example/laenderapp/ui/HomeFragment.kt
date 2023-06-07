package com.example.laenderapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.laenderapp.R
import com.example.laenderapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    //wir nutzen binding für unser fragment und deklarieren eine leere variable vom Typ FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

    //in der onCreateView wird das layout grob erstellt, container, größe etc
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //hier setzten/laden wir auf die leere erstellte variable das richtige Layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    //in der onViewCreated greifen wir auf das layout zu und befüllen es mit den korrekten Daten
    //wir benutzen den navcontroller um unsere navigation aus dem nav_graph durchzuführen
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.europeCountriesLiveData.observe(
            viewLifecycleOwner
        ) {
            Log.d("liveData", "$it")
        }

        binding.mcHomeAccountImage.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogFragment())
        }

        binding.mcHomeNewGame.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameSelectionFragment())
        }
/*
        binding.mcHomeContinueGame.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuizFragment())
        }*/
    }
}