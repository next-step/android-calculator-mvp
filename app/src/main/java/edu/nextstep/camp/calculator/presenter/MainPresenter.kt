package edu.nextstep.camp.calculator.presenter

import edu.nextstep.camp.calculator.MainContract
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.model.RecordStatement
import edu.nextstep.camp.calculator.model.Result

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val expression = Expression()
    private val _recordStatementList = mutableListOf<RecordStatement>()
    override val recordStatementList: List<RecordStatement> = _recordStatementList

    override fun calculate(statement: String) {
        runCatching {
            val result = expression.calculatedValue(statement)
            view.showExpression(result)
            saveStatement(statement, Result(result))
        }.onFailure {
            view.showError(it.message.toString())
        }
    }

    override fun appendOperand(statement: String, operand: String) {
        val appendedStatement = expression.appendOperand(statement, operand)
        view.showExpression(appendedStatement)
    }

    override fun appendOperator(statement: String, operator: String) {
        val appendedStatement = expression.appendOperator(statement, operator)
        view.showExpression(appendedStatement)
    }

    override fun deleteLastElement(statement: String) {
        val deletedStatement = expression.deleteLastElement(statement)
        view.showExpression(deletedStatement)
    }

    override fun saveStatement(statement: String, result: Result) {
        _recordStatementList.add(
            0,
            RecordStatement(
                expression = statement,
                result = result
            )
        )
        view.saveAddedStatement()
    }
}