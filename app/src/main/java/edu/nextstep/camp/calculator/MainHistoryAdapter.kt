package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

class MainHistoryAdapter :
    ListAdapter<History, MainHistoryAdapter.MainHistoryHolder>(HistoryDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHistoryHolder {
        return MainHistoryHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHistoryHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class MainHistoryHolder(private val binding: ItemResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.tvExpression.text = history.Expression
            binding.tvResult.text = history.resultValue
        }
    }
}

class HistoryDiffUtilCallback : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.Expression == newItem.Expression && newItem.resultValue == oldItem.resultValue
    }

}