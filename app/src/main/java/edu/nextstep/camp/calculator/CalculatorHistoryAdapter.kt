package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationResult

class CalculatorHistoryAdapter : RecyclerView.Adapter<ResultViewHolder>() {
    private val items = mutableListOf<CalculationResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder =
        ResultViewHolder(parent)

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<CalculationResult>) {
        items.clear()
        items.addAll(list)
    }

    override fun getItemCount() = items.size

}

class ResultViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
) {
    private val binding = ItemResultBinding.bind(itemView)

    fun bind(result: CalculationResult) {
        with(binding) {
            tvExpression.text = result.expression
            tvResult.text = result.result.toString()
        }
    }
}
