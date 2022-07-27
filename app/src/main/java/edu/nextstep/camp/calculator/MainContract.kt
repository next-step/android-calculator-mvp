package edu.nextstep.camp.calculator

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(result: String)
        fun showError(message: String)
        fun showMemory(histories: List<Memory.MemoryItem>)
    }

    interface Presenter {
        fun addToExpression(operator: Operator)
        fun addToExpression(operand: Int)
        fun calculateExpression()
        fun removeLastFromExpression()
        fun getMemory()
        fun toggleMemoryView(recyclerView: RecyclerView, textView: TextView): Boolean
    }
}