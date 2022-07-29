package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculateResult
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun onFailCalculate()
        fun addCalculateResult(calculateResult: CalculateResult)
    }

    interface Presenter {
        fun input(operand: Int)
        fun input(operator: Operator)
        fun removeLast()
        fun calculate()
        fun initExpression(result : Int)
    }
}
