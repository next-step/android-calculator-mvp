package edu.nextstep.camp.calculator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import edu.nextstep.camp.calculator.R
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MemoryRecyclerViewAdapter : ListAdapter<MemoryUIModel, MemoryViewHolder>(MemoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding: ItemResultBinding = ItemResultBinding.inflate(inflate, parent, false)
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val item = if (position >= itemCount) return else getItem(position)
        holder.bind(item)
    }
}