package edu.nextstep.camp.calculator

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ViewHolder(view: View, binding: ItemResultBinding) : RecyclerView.ViewHolder(view) {
    val expression: TextView = binding.tvExpression
    val result: TextView = binding.tvResult
}