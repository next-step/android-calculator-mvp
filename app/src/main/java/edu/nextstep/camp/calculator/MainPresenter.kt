package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.RecordData

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private val recordList: MutableList<RecordData> = mutableListOf()
): MainContract.Presenter {

    override fun inputNumber(number: Int) {
        expression += number

        view.showCalculateExpression(expression.toString())
    }

    override fun inputOperator(operatorData: Operator) {
        expression += operatorData

        view.showCalculateExpression(expression.toString())
    }

    override fun removeLastIndexData() {
        expression = expression.removeLast()

        view.showCalculateExpression(expression.toString())
    }

    override fun loadCalculatorRecord() {
        view.loadRecordList(recordList)
    }

    override fun calculateInputValue() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showCompletionOfExpressionDataMessage()
            return
        }

        recordList.add(RecordData(expression.toString(), result))
        expression = Expression.EMPTY + result

        view.showCalculateExpression(expression.toString())
    }

    override fun clickCalculatorRecord(isShowRecord: Boolean) {
        if (isShowRecord) {
            view.showRecord()
        } else {
            view.hideRecord()
        }
    }

}