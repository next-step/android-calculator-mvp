package edu.nextstep.camp.calculator

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.nextstep.camp.calculator.History.Companion.historyDiffUtil

class HistoryAdapter : ListAdapter<History, HistoryViewHolder>(historyDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder.create(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}