package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculateResult

class CalculatorResultAdapter: ListAdapter<CalculateResult, CalculatorResultViewHolder>(diffUtil) {
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
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CalculateResult>() {
            override fun areContentsTheSame(oldItem: CalculateResult, newItem: CalculateResult) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: CalculateResult, newItem: CalculateResult) =
                oldItem.expression == newItem.expression
        }
    }
}

class CalculatorResultViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(calculateResult: CalculateResult) {
        binding.tvExpression.text = calculateResult.expression.toString()
        val resultText = "=${calculateResult.result}"
        binding.tvResult.text = resultText
    }
}
