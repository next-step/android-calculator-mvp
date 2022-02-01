package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.model.RecordStatement
import edu.nextstep.camp.calculator.model.Result

interface MainContract {
    interface View {
        fun showExpression(expression: String?)
        fun showError(errorMessage: String)
        fun saveAddedStatement()
    }

    interface Presenter {
        val recordStatementList: List<RecordStatement>
        fun calculate(statement: String)
        fun appendOperand(statement: String, operand: String)
        fun appendOperator(statement: String, operator: String)
        fun deleteLastElement(statement: String)
        fun saveStatement(statement: String, result: Result)
    }
}