package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.laenderapp.R
import com.example.laenderapp.databinding.FragmentQuizBinding

// Für Notizen siehe HomeFragment
class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mcQuizCityArrowLeft.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToContinentsFragment())
        }

        binding.mcQuizCityHome.setOnClickListener {
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToHomeFragment())
        }

        binding.ivQuizFlag.setImageResource(R.drawable.deutschland)
    }

}