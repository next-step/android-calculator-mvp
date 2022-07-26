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

    override fun addElement(operator: Operator) =
        updateViewState(state.addElement(operator))

    override fun addElement(operand: Operand) =
        updateViewState(state.addElement(operand))

    override fun removeElement() =
        updateViewState(state.removeElement())

    override fun calculate() {
        runCatching {
            StringCalculator.calculate(state)
        }
            .onSuccess(view::setCalculationResult)
            .onFailure {
                view.calculationFailed()
            }
    }

    private fun updateViewState(state: StringExpressionState) {
        this.state = state.also(view::setExpression)
    }
}
