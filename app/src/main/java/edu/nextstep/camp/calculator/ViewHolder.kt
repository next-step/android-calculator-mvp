package edu.nextstep.camp.calculator

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val expression: TextView = view.findViewById(R.id.tv_expression)
    val result: TextView = view.findViewById(R.id.tv_result)
}