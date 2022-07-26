package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState

class MainPresenter(
    private val view: MainContract.View,
    initialState: StringExpressionState
) : MainContract.Presenter {

    private var state: StringExpressionState = initialState

    override fun addElement(operator: Operator) {
        state = state
            .addElement(operator)
            .also(view::setExpression)
    }

    override fun addElement(operand: Operand) {
        state = state
            .addElement(operand)
            .also(view::setExpression)
    }

    override fun removeElement() {
        state = state
            .removeElement()
            .also(view::setExpression)
    }

    override fun calculate() {
        runCatching {
            StringCalculator.calculate(state)
        }
            .onSuccess(view::setCalculationResult)
            .onFailure {
                view.calculationFailed()
            }
    }
}
