package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 20..
 */
interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showInCompleteExpressionMessage()
        fun setItems(items: List<History>)
        fun updateItems()
    }

    interface Presenter {
        fun enterNumber(number: Int)
        fun enterOperator(operator: Operator)
        fun removeLast()
        fun calculate()
        fun loadHistory()
    }
}