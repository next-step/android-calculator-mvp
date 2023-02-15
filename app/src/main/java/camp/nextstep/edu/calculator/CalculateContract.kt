package camp.nextstep.edu.calculator

import com.example.domain.Operator

interface CalculateContract {
    interface View {
        fun showExpression(expression: String)
        fun showErrorMessage(errorMessage: String)
    }

    interface Presenter {
        fun appendOperand(operand: Int)
        fun appendOperator(op: Operator)
        fun removeLastValue()
        fun calculateExpression()
    }
}