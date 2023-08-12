package camp.nextstep.edu.calculator

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation

interface Contract {
    interface View {
        val presenter: Presenter

        fun display(text: String)
        fun displayExpressionError()
    }

    interface Presenter {
        fun addOperandExpression(operand: Operand)
        fun addOperationExpression(operation: Operation)
        fun removeExpressionItem()
        fun calculate()
    }
}
