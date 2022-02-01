package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.model.CalculateResult
import edu.nextstep.camp.calculator.model.RecordStatement

interface MainContract {
    interface View {
        fun showExpression(expression: String?)
        fun showError(errorMessage: String)
        fun showSavedStatement(recordStatement: RecordStatement)
    }

    interface Presenter {
        val recordStatementList: List<RecordStatement>
        fun calculate(statement: String)
        fun appendOperand(statement: String, operand: String)
        fun appendOperator(statement: String, operator: String)
        fun deleteLastElement(statement: String)
        fun saveStatement(statement: String, calculateResult: CalculateResult)
    }
}