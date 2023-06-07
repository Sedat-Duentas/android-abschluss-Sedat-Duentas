package com.example.laenderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.laenderapp.data.datamodels.Adaptermodel
import com.example.laenderapp.databinding.ItemLayoutBinding
import com.example.laenderapp.ui.ContinentsFragmentDirections
import com.example.laenderapp.ui.MainViewModel

// Die Liste von Kontinenten wird übergeben und RecyclerView wird vorbereitet
class ContinetsAdapter (
    private val dataset: List<Adaptermodel>,
    private val viewModel: MainViewModel,
    private val context: Context
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
        val item = dataset[position]
        
        holder.binding.ivItem.setImageResource(item.imageResource)
        holder.binding.tvItem.setText(item.stringResource)

        holder.binding.mcItem.setOnClickListener {
            val continentId = item.stringResource
            val resourceName = context.resources.getResourceName(continentId)
            val continentName = resourceName.substringAfterLast("/")
            viewModel.selectContinent(continentName)
            holder.binding.mcItem.findNavController()
                .navigate(ContinentsFragmentDirections.actionContinentsFragmentToQuizFragment(item.stringResource))

                // Hier wird die Information, welcher Kontinent als Argument übergeben wird, mitgegeben
        }
    }

    override fun getItemCount(): Int {
        return dataset.size // Damit der Layoutmanager weiß wie lang die Liste ist
    }
}