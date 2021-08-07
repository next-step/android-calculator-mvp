package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private var expression: Expression = Expression.EMPTY
) : MainContract.Presenter {

    override fun expression(number: Int): Expression {
        expression += number
        return expression
    }

    override fun expression(operator: Operator): Expression {
        expression += operator
        return expression
    }
}
