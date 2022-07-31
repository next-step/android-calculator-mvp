package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.RecordData

class CalculateRecordAdapter : ListAdapter<RecordData, RecordViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {

        val itemResultBinding: ItemResultBinding = ItemResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecordViewHolder(itemResultBinding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.bindRecordData(getItem(position))
        }
    }

    fun setRecordItemList(itemList: List<RecordData>) {
        submitList(itemList)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecordData>() {

            override fun areContentsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
                return oldItem.expression == newItem.expression
            }
        }
    }
}

class RecordViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindRecordData(recordData: RecordData) {
        binding.history = recordData
    }
}