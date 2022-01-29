package edu.nextstep.camp.calculator.view

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.model.Statement

class RecordViewHolder(private val view: ItemResultBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(statement: Statement) {
        view.tvExpression.text = statement.expression
        view.tvResult.text = statement.result.toString()
    }
}