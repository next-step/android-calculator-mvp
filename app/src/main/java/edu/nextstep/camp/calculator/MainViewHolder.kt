package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_result, parent, false)
) {
    private val binding: ItemResultBinding = ItemResultBinding.bind(itemView)

    fun bind(history: HistoryDto) = with(binding) {
        tvExpression.text = history.expression
        tvResult.text = history.result
    }
}
