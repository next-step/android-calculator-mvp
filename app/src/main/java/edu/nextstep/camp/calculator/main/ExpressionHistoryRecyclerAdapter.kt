package edu.nextstep.camp.calculator.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.ExpressionHistory

class ExpressionHistoryRecyclerAdapter :
    ListAdapter<ExpressionHistory, ExpressionHistoryViewHolder>(object :
        DiffUtil.ItemCallback<ExpressionHistory>() {
        override fun areItemsTheSame(
            oldItem: ExpressionHistory,
            newItem: ExpressionHistory
        ): Boolean {
            return oldItem.expression.toString() == newItem.expression.toString()
        }

        override fun areContentsTheSame(
            oldItem: ExpressionHistory,
            newItem: ExpressionHistory
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpressionHistoryViewHolder {
        val binding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpressionHistoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ExpressionHistoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}