package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateResult

class CalculatorResultAdapter: RecyclerView.Adapter<CalculatorResultViewHolder>() {
    private val calculatorHistories = mutableListOf<CalculateResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculatorResultViewHolder {
        return CalculatorResultViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CalculatorResultViewHolder, position: Int) {
        holder.onBind(calculatorHistories[position])
    }

    override fun getItemCount(): Int = calculatorHistories.size

    fun putCalculatorResult(calculatorHistory: CalculateResult) {
        this.calculatorHistories.add(calculatorHistory)
        notifyDataSetChanged()
//        notifyItemInserted(calculatorHistories.size - 1)
    }
}

class CalculatorResultViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateResult) {
        binding.tvExpression.text = calculateResult.expression.toString()
        binding.tvResult.text = "=".plus(calculateResult.result)
    }
}
