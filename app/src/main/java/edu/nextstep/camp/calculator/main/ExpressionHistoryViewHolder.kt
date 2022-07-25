package edu.nextstep.camp.calculator.main

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistory
import java.util.*

class ExpressionHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ExpressionHistory) {
        binding.tvExpression.text = item.expression.toString()
        binding.tvResult.text = String.format(Locale.getDefault(), "= %d", item.result)
    }
}