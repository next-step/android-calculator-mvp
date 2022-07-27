package edu.nextstep.camp.calculator.view

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MemoryViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(memory: MemoryUIModel) {
        binding.tvExpression.text = memory.expressionText
        binding.tvResult.text = memory.resultText
    }
}