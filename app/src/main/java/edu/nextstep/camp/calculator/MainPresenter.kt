package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

class MainPresenter(
    private var expression: Expression = Expression.EMPTY
) {

    fun expression(number: Int): Expression {
        expression += number
        return expression
    }
}
