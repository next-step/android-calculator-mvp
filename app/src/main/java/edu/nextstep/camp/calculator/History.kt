package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.Expression

data class History(
    val expression: Expression,
    val result: String
) {

    companion object {
        val historyDiffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean =
                oldItem.expression == newItem.expression

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean =
                oldItem == newItem
        }
    }

}
