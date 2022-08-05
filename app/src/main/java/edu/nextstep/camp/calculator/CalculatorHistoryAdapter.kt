package edu.nextstep.camp.calculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem

class CalculatorHistoryAdapter : RecyclerView.Adapter<CalculatorHistoryAdapter.ViewHolder>() {

    private val historyExpressions = mutableListOf<ExpressionHistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
            .let { view -> ViewHolder(view) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyExpressions[position])
    }

    override fun getItemCount() = historyExpressions.size

    fun clearAndAddAllHistoryExpressions(expressions: List<ExpressionHistoryItem>) =
        historyExpressions.apply {
            clear()
            addAll(expressions)
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemResultBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: ExpressionHistoryItem) {
            binding.tvExpression.text = item.expression
            binding.tvResult.text = "= ${item.result}"
        }
    }
}
