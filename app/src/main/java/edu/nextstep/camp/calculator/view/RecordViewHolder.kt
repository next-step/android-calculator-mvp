package edu.nextstep.camp.calculator.view

import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding
import edu.nextstep.camp.calculator.model.RecordStatement

class RecordViewHolder(private val view: ItemResultBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(recordStatement: RecordStatement) {
        view.tvExpression.text = recordStatement.expression
        view.tvResult.text = recordStatement.result.toString()
    }
}