package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.Memory.MemoryItem

class MemoryAdapter() :
    RecyclerView.Adapter<MemoryAdapter.MemoryHolder>() {
    var datalist = listOf<MemoryItem>()

    inner class MemoryHolder(private val binding: ItemResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MemoryItem) {
            binding.tvExpression.text = data.expression
            binding.tvResult.text = "= ${data.result}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoryHolder(binding)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MemoryHolder, position: Int) {
        holder.bind(datalist[position])
    }
}