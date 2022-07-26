package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.CalculationResult

class CalculationHistoryAdapter(calculationResult: List<CalculationResult> = emptyList()) :
    RecyclerView.Adapter<CalculationHistoryViewHolder>() {
    private var dataList = calculationResult

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalculationHistoryViewHolder =
        CalculationHistoryViewHolder(
            ItemResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CalculationHistoryViewHolder, position: Int) =
        holder.onBind(dataList[position])

    override fun getItemCount(): Int = dataList.size

    fun changeCalculateResultList(newDataList: List<CalculationResult>) {
        val diffCallback = DiffUtilCallback(dataList, newDataList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList = newDataList
        diffResult.dispatchUpdatesTo(this)
    }

}

class CalculationHistoryViewHolder(private val binding: ItemResultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: CalculationResult) {
        with(binding) {
            tvExpression.text = data.expression.toString()
            tvResult.text = "= ${data.result}"
        }
    }
}

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