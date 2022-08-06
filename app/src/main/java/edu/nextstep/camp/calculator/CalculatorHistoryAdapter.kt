package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem

class CalculatorHistoryAdapter :
    ListAdapter<ExpressionHistoryItem, CalculatorHistoryAdapter.ViewHolder>(
        ExpressionHistoryItemDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
            .let { view -> ViewHolder(view) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemResultBinding.bind(view)
        fun bind(item: ExpressionHistoryItem) {
            val result = "= ${item.result}"
            binding.tvExpression.text = item.expression
            binding.tvResult.text = result
        }
    }
}

class ExpressionHistoryItemDiffCallback : DiffUtil.ItemCallback<ExpressionHistoryItem>() {
    override fun areContentsTheSame(
        oldItem: ExpressionHistoryItem,
        newItem: ExpressionHistoryItem
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: ExpressionHistoryItem,
        newItem: ExpressionHistoryItem
    ) = oldItem.expression == newItem.expression
}
