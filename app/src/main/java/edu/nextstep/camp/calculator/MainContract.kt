package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View : BaseView<Presenter> {
        // TextView 에 Expression 데이터를 보여준다
        fun showExpression(expression: String)
        // 에러 메시지 보여준다
        fun showError()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLastInExpression()
        fun evaluateExpression()
    }
}