package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

/**
 * 계산기록리스트 adapter
 * Created by jeongjinhong on 2022. 07. 24..
 */
class CalculationMemoryAdapter() :
    ListAdapter<Pair<String, Int>, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemResultBinding =
            ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemResultBinding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.setData(getItem(holder.adapterPosition))
        }
    }

    inner class Holder(
        private val binding: ItemResultBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Pair<String, Int>) {
            binding.tvExpression.text = data.first
            binding.tvResult.text = "= ${data.second}"
        }
    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Pair<String, Int>>() {
        override fun areItemsTheSame(
            oldItem: Pair<String, Int>,
            newItem: Pair<String, Int>
        ): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<String, Int>,
            newItem: Pair<String, Int>
        ): Boolean {
            return oldItem == newItem
        }
    }
}

