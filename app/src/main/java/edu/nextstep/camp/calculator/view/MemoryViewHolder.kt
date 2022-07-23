package edu.nextstep.camp.calculator.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.R

class MemoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textViewExpression: TextView
    var textViewResult: TextView

    init {
        textViewExpression = itemView.findViewById(R.id.tv_expression)
        textViewResult = itemView.findViewById(R.id.tv_result)
    }

    fun bind(memory: MemoryUIModel) {
        textViewExpression.text = memory.expressionText
        textViewResult.text = memory.resultText
    }
}