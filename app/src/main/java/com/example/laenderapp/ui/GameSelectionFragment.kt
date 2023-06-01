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

// Für Notizen siehe HomeFragment
class GameSelectionFragment : Fragment() {

    private lateinit var binding: FragmentGameSelectionBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_selection, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.selectionCardViewFlags.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment())
        }

        binding.selectionCardViewCity.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment())
        }

        binding.selectionCardViewCurrency.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToContinentsFragment())
        }

        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(GameSelectionFragmentDirections.actionGameSelectionFragmentToHomeFragment())
        }
    }
}