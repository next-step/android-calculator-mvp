package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 20..
 */
class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    override fun enterNumber(number: Int) {
        expression += number

        view.showExpression(expression.toString())
    }

    override fun enterOperator(operator: Operator) {
        expression += operator

        view.showExpression(expression.toString())
    }

    override fun delete() {
        expression = expression.removeLast()

        view.showExpression(expression.toString())
    }
}