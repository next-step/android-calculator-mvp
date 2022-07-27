package edu.nextstep.camp.calculator

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.Memory

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression: Expression = Expression()
    private var history: Memory = Memory()
    val calculator: Calculator = Calculator()

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
            ?: return view.showError("완성되지 않은 수식입니다.")
        history += Memory.MemoryItem(expression = expression.toString(), result = result.toString())
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun toggleMemoryView(recyclerView: RecyclerView, textView:TextView) : Boolean {
        if (recyclerView.isVisible) {
            recyclerView.visibility = View.INVISIBLE
            textView.visibility = View.VISIBLE
            return false
        }
        textView.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
        return true
    }

    override fun getMemory() {
        val histories = history.getHistory()
        view.showMemory(histories)
    }
}