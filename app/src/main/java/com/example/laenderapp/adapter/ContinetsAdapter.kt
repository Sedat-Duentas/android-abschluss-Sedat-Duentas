package com.example.laenderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.laenderapp.ContinentsFragmentDirections
import com.example.laenderapp.data.model.Continent
import com.example.laenderapp.databinding.FragmentContinentsBinding
import com.example.laenderapp.databinding.ItemLayoutBinding

class ContinetsAdapter (
    private val dataset: List<Continent>
) : RecyclerView.Adapter<ContinetsAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.categoryLayout.setBackgroundResource(item.imageResource)
        holder.binding.categoryCard.setOnClickListener {
            holder.binding.categoryCard.findNavController()
                .navigate(ContinentsFragmentDirections.actionContinentsFragmentToQuizFragment())
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}