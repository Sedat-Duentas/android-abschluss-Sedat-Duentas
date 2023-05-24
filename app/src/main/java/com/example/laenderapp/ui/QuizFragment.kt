package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.laenderapp.R
import com.example.laenderapp.data.datamodels.Continents
import com.example.laenderapp.databinding.FragmentQuizBinding
import kotlin.random.Random

// Für Notizen siehe HomeFragment
class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    private val viewModel: MainViewModel by activityViewModels()

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

        viewModel.continents.observe(viewLifecycleOwner) {

        }

        val url = "https://flagcdn.com/256x192/za.png"

        binding.ivQuizFlag.load(url.toUri().buildUpon().scheme("https").build())

        var btn = listOf(binding.btn1Quiz, binding.btn2Quiz, binding.btn3Quiz, binding.btn4Quiz)

        var randomBtn = btn.random()

        randomBtn.text = "Deutschland"



        //var flagRandom =

    }

}