package edu.nextstep.camp.calculator

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    private val histories = mutableListOf<HistoryDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(parent = parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(histories[position])
    }

    override fun getItemCount(): Int = histories.size

    fun notify(histories: List<HistoryDto>) {
        this.histories.run {
            clear()
            addAll(histories)
        }
    }
}
