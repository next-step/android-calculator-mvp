package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringCalculator
import edu.nextstep.camp.calculator.domain.StringExpressionState

class MainPresenter(
    private val view: MainContract.View,
    initialState: StringExpressionState,
    initialRecordsShowing: Boolean = false,
) : MainContract.Presenter {

    private val records: MutableList<Record> = mutableListOf()
    private var state: StringExpressionState = initialState
    private var isRecordsShowing: Boolean = initialRecordsShowing

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
            .onSuccess {
                view.setCalculationResult(it)
                records.add(Record(state = state, result = it))
            }
            .onFailure {
                view.calculationFailed()
            }
    }

    override fun toggleRecords() {
        if (isRecordsShowing) view.closeRecords() else view.showRecords(records.toList())
        isRecordsShowing = !isRecordsShowing
    }

    private fun updateViewState(state: StringExpressionState) {
        this.state = state.also(view::setExpression)
    }
}
