package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.StringExpressionState

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {

    override fun addElement(rawExpression: String, operand: Operand) =
        StringExpressionState
            .of(rawExpression)
            .addElement(operand)
            .let(view::setExpression)

}
