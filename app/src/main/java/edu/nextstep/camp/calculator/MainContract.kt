package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator

interface MainContract {
    interface View : BaseView<Presenter> {
        fun displayExpression(expression: String)
        fun handleExceptions(throwable: Throwable)
        fun showEvaluationHistory(history: List<EvaluationRecord>)
        fun hideEvaluationHistory()
    }
    interface Presenter {
        fun addOperatorToken(operator: Operator)
        fun addOperandToken(operand: Operand)
        fun evaluate()
        fun delete()
        fun toggleEvaluationHistory()
    }
}
