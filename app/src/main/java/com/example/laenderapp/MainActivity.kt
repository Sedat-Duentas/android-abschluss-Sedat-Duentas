package com.example.laenderapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.laenderapp.ui.MainViewModel

// Hier wird nichts geändert da, jetzt alles in den Fragmenten passiert
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        // Ohne das geht es nicht!!!
        viewModel.europeCountriesLiveData.observe(
            this
        ) {
            Log.d("viemodelTest","$it" )
        }

        return super.onCreateView(name, context, attrs)
    }
}