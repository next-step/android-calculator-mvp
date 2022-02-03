package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Record

class MainHistoryAdapter(
    private var history: History
) : RecyclerView.Adapter<MainHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(history.records[position])
    }

    override fun getItemCount(): Int {
        return history.records.size
    }

    fun refreshHistory(history: History) {
        this.history = history
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvExpression = itemView.findViewById<TextView>(R.id.tv_expression)
        private val tvResult = itemView.findViewById<TextView>(R.id.tv_result)

        fun bind(record: Record) {
            tvExpression.text = record.expression
            tvResult.text = record.result
        }
    }
}
