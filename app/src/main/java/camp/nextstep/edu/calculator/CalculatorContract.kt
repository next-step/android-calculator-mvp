package camp.nextstep.edu.calculator

import com.nextstep.edu.domain.Operator

interface CalculatorContract {

    interface View {
        var presenter: Presenter

        fun alertIncompleteExpression()
        fun showCalculationExpression(expression: String)
    }

    interface Presenter {
        fun appendExpression(operand: Int)
        fun appendExpression(operator: Operator)
        fun removeExpression()
        fun calculate()
    }
}