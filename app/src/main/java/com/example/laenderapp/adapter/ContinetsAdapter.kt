package com.example.laenderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.laenderapp.data.datamodels.Adaptermodel
import com.example.laenderapp.databinding.ItemLayoutBinding
import com.example.laenderapp.ui.CityQuizFragmentDirections
import com.example.laenderapp.ui.ContinentsFragmentDirections

// Die Liste von Kontinenten wird übergeben und RecyclerView wird vorbereitet
class ContinetsAdapter (
    private val dataset: List<Adaptermodel>, private val gameMode: String
) : RecyclerView.Adapter<ContinetsAdapter.ItemViewHolder>() {

    // Der Viewholder weiß welche Teile des Layouts beim Recycling angepasst werden
    class ItemViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // Hier werden neue Viewholder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Das Itemlayout wird gebaut
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Und in einem Viewholder zurückgegeben
        return ItemViewHolder(binding)
    }

    // Hier wird die "leere" liste mit den korrekten daten befüllt
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position] // Das Kontinent wird bestimmt
        holder.binding.ivItem.setImageResource(item.imageResource) // Bild vom Kontinent wird geladen
        holder.binding.tvItem.setText(item.stringResource) // Text vom Kontinent wird geladen

        // Hier wird die Information, welche Spielart als Argument übergeben wird, mitgegeben
        holder.binding.mcItem.setOnClickListener {
            when (gameMode) {
                "currency" -> {
                    val action = ContinentsFragmentDirections.actionContinentsFragmentToCurrencyQuizFragment(item.stringResource)
                    holder.binding.mcItem.findNavController().navigate(action)
                }
                "flag" -> {
                    val action = ContinentsFragmentDirections.actionContinentsFragmentToQuizFragment(item.stringResource)
                    holder.binding.mcItem.findNavController().navigate(action)
                }
                "city" -> {
                    val action = ContinentsFragmentDirections.actionContinentsFragmentToQuizFragment(item.stringResource)
                    holder.binding.mcItem.findNavController().navigate(action)
                }
                else -> {
                    // Fallback-Verhalten, falls der gameMode unbekannt ist
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size // Damit der Layoutmanager weiß wie lang die Liste ist
    }
}