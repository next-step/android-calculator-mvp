package edu.nextstep.camp.calculator

import edu.nextstep.camp.common.UiText
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showResult(result: Int)
        fun showErrorMessage(uiText: UiText)
    }
    
    interface Presenter {
        fun addNumberToExpression(number: Int)
        fun addOperatorToExpression(operator: Operator)
        fun removeLastToken()
        fun calculateCurrentExpression()
    }
}