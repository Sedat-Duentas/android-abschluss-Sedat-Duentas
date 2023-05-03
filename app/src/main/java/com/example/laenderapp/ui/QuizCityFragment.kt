package com.example.laenderapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.laenderapp.R
import com.example.laenderapp.databinding.FragmentQuizCityBinding

class QuizCityFragment : Fragment() {

    private lateinit var binding: FragmentQuizCityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_city, container, false)
        return binding.root
    }
}