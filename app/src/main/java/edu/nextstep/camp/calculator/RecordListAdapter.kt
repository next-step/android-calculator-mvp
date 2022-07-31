package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.RecordData

class HistoryAdapter : ListAdapter<RecordData, RecordViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setItems(itemList: List<RecordData>) {
        submitList(itemList)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecordData>() {
            override fun areContentsTheSame(oldItem: RecordData, newItem: RecordData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: RecordData, newItem: RecordData) =
                oldItem.expression == newItem.expression
        }
    }
}

class RecordViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(history: RecordData) {
        binding.history = history
    }
}