package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculateResult
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun onFailCalculate()
        fun showCalculateHistory(calculateResults: List<CalculateResult>)
        fun hideCalculateHistory()
    }

    interface Presenter {
        fun input(operand: Int)
        fun input(operator: Operator)
        fun removeLast()
        fun calculate()
        fun initExpression(result : Int)
        fun toggleCalculatorHistory()
    }
}
