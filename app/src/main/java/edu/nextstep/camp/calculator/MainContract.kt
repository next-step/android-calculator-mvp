package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.CalculationHistory
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showResult(result: Int)
        fun showErrorMessage(exception: Exception)
        fun showCalculationHistoryList(calculationHistoryList: List<CalculationHistory>)
        fun showCalculatorUi()
    }
    
    interface Presenter {
        fun addNumberToExpression(number: Int)
        fun addOperatorToExpression(operator: Operator)
        fun removeLastToken()
        fun calculateCurrentExpression()
        fun toggleUiBetweenCalculatorOrHistory()
    }
}