package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateResult

class CalculatorResultAdapter: RecyclerView.Adapter<CalculatorResultViewHolder>() {
    private val calculatorResults = mutableListOf<CalculateResult>()

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
        holder.onBind(calculatorResults[position])
    }

    override fun getItemCount(): Int = calculatorResults.size

    fun setCalculatorResults(calculatorResults: List<CalculateResult>) {
        this.calculatorResults.clear()
        this.calculatorResults.addAll(calculatorResults)
        notifyDataSetChanged()
    }
}

class CalculatorResultViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateResult) {
        binding.tvExpression.text = calculateResult.expression.toString()
        binding.tvResult.text = binding.root.resources.getString(R.string.calculate_result, calculateResult.result)
    }
}
