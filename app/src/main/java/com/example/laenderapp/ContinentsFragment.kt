package com.example.laenderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
}