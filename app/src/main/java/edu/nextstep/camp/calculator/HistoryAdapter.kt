package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.domain.History

/**
 * Created by link.js on 2022. 07. 23..
 */
class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {
    private var itemList = listOf<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ItemResultBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(itemList: List<History>) {
        this.itemList = itemList
    }
}

class HistoryViewHolder(private val binding: ItemResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(history: History) {
        binding.history = history
    }
}