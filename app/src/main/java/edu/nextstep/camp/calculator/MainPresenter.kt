package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memory
import edu.nextstep.camp.calculator.domain.Operator
import java.lang.IllegalArgumentException

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val memory = Memory()
    private val calculator = Calculator()

    private var viewType: CalculatorViewType = ExpressionView

    override fun addNumber(expression: Expression, number: String) {
        if (number.toIntOrNull() == null) {
            throw IllegalArgumentException("$number is not Int")
        }

        val newExpression = expression + number.toInt()
        view.onViewUpdated(newExpression)
    }

    override fun addOperator(expression: Expression, operator: Operator) {
        val newExpression  = expression + operator
        view.onViewUpdated(newExpression)
    }

    override fun delete(expression: Expression) {
        val newExpression = expression.removeLast()
        view.onViewUpdated(newExpression)
    }

    override fun calculate(expression: Expression) {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.onExpressionIncomplete()
            return
        }

        memory.add(expression, result)
        view.onViewUpdated(Expression.EMPTY + result)
    }

    override fun toggleViewType() {
        viewType = viewType.toggle()
        view.onViewTypeChanged(viewType, memory)
    }
}