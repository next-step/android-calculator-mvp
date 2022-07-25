package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator

interface MainContract {
    interface View : BaseView<Presenter> {
        fun displayExpression(expression: String)
        fun handleExceptions(throwable: Throwable)
    }
    interface Presenter {
        fun addOperatorToken(operator: Operator)
        fun addOperandToken(operand: Operand)
        fun evaluate()
        fun delete()
    }
}
