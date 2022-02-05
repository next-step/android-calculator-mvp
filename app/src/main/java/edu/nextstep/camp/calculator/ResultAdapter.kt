package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.ResultHandler

class ResultAdapter(private val resultHandler: ResultHandler) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expression.text = resultHandler.getResults()[position].expression
        holder.result.text = resultHandler.getResults()[position].result
    }

    override fun getItemCount(): Int {
        return resultHandler.getResults().size
    }
}