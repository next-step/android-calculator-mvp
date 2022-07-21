package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private var expression = Expression.EMPTY

    override fun enterNumber(number: Int) {
        expression += number
        view.showExpression(expression.toString())
    }

    override fun enterOperator(operator: Operator) {
        TODO("Not yet implemented")
    }

    override fun calculate() {
        TODO("Not yet implemented")
    }

    override fun removeLast() {
        TODO("Not yet implemented")
    }
}
