package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.HistoryData

class HistoryAdapter : ListAdapter<HistoryData, HistoryAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = currentList.size

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<HistoryData>() {
            override fun areContentsTheSame(oldItem: HistoryData, newItem: HistoryData) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: HistoryData, newItem: HistoryData) =
                oldItem.expression == newItem.expression
        }
    }

    class ViewHolder(private val binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(historyData: HistoryData) {
            binding.tvExpression.text = historyData.expression.toString()
            val result = "= ${historyData.result}"
            binding.tvResult.text = result
        }
    }
}