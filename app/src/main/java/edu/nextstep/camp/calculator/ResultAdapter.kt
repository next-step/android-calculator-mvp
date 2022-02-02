package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.Result

class ResultAdapter(private val expressionSet: ArrayList<Result>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expression: TextView
        val result: TextView

        init {
            expression = view.findViewById(R.id.tv_expression)
            result = view.findViewById(R.id.tv_result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expression.text = expressionSet[position].expression
        holder.result.text = expressionSet[position].result
    }

    override fun getItemCount(): Int {
        return expressionSet.size
    }
}