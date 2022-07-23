package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class CalculatorHistoryAdapter : RecyclerView.Adapter<CalculatorHistoryAdapter.ViewHolder>() {

    var data = emptyList<CalculationHistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
            .let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemResultBinding.bind(view)

        fun bind(item: CalculationHistoryItem) {
            binding.tvExpression.text = item.rawExpression
            binding.tvResult.text = "= ${item.result}"
        }
    }
}