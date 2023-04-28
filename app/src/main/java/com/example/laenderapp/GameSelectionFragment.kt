package com.example.laenderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.laenderapp.databinding.FragmentGameSelectionBinding

class GameSelectionFragment : Fragment() {

    private lateinit var binding: FragmentGameSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_selection, container, false)
        return binding.root
    }
}