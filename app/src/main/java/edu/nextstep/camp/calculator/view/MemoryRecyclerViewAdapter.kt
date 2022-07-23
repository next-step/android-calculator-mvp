package edu.nextstep.camp.calculator.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.R

class MemoryRecyclerViewAdapter(private var list: List<MemoryUIModel>) : RecyclerView.Adapter<MemoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return MemoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        val item = if (position >= list.size) return else list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun appendItems(newList: List<MemoryUIModel>) {
        val currentPosition: Int = list.size -1
        val mutableList = list.toMutableList()
        mutableList.addAll(newList)
        list = mutableList
        notifyItemRangeChanged(currentPosition, list.size)
    }
}