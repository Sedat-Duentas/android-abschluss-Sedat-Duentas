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
    private lateinit var binding: FragmentContinentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_continents, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = binding.recyclerViewHome

        val continents = Repository().loadContinents()

        recyclerView.adapter = ContinetsAdapter(continents)

        recyclerView.setHasFixedSize(true)

        binding.mcArrowLeft.setOnClickListener {
            findNavController().navigate(ContinentsFragmentDirections.actionContinentsFragmentToGameSelectionFragment())
        }

        binding.mcContinentsHome.setOnClickListener {
            findNavController().navigate(ContinentsFragmentDirections.actionContinentsFragmentToHomeFragment())
        }

    }
}