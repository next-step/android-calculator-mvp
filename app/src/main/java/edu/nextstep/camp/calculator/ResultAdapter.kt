package edu.nextstep.camp.calculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dodobest.domain.ResultHandler
import edu.nextstep.camp.calculator.databinding.ItemResultBinding

class ResultAdapter(private val resultHandler: ResultHandler = ResultHandler()) : RecyclerView.Adapter<ViewHolder>() {
    fun getResultHandler() : ResultHandler {
        return resultHandler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemResultBinding = ItemResultBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.expression.text = resultHandler.getResults()[position].expression
        holder.result.text = resultHandler.getResults()[position].result
    }

    override fun getItemCount(): Int {
        return resultHandler.getResults().size
    }
}