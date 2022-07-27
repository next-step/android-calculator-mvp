package edu.nextstep.camp.calculator

import androidx.recyclerview.widget.DiffUtil
import edu.nextstep.camp.calculator.domain.CalculationResult

class DiffUtilCallback(
    private val oldList: List<CalculationResult>,
    private val newList: List<CalculationResult>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}