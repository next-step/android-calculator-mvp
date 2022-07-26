package edu.nextstep.camp.calculator.view

import androidx.recyclerview.widget.DiffUtil

class MemoryDiffCallback : DiffUtil.ItemCallback<MemoryUIModel>() {
    override fun areItemsTheSame(oldItem: MemoryUIModel, newItem: MemoryUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemoryUIModel, newItem: MemoryUIModel): Boolean {
        return oldItem == newItem
    }
}