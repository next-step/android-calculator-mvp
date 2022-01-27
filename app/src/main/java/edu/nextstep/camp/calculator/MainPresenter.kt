package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val expression = Expression()

    override fun calculate(statement: String) {
        val result = expression.calculatedValue(statement)
        view.refreshCalculator(result)
    }

    override fun appendOperand(statement: String, operand: String) {
        val appendedStatement = expression.appendStatement(statement, operand)
        view.refreshCalculator(appendedStatement)
    }

    override fun appendOperator(statement: String, operator: String) {
        if (statement.isNotEmpty()) {
            val appendedStatement = expression.appendStatement(statement, operator)
            view.refreshCalculator(appendedStatement)
        }
    }

    override fun deleteLastElement(statement: String) {
        val deletedStatement = expression.deleteLastElement(statement)
        view.refreshCalculator(deletedStatement)
    }
}