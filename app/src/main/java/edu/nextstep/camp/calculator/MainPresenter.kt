package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
<<<<<<< HEAD
import edu.nextstep.camp.calculator.domain.RecordData

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private val recordList: MutableList<RecordData> = mutableListOf()
) : MainContract.Presenter {
=======

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY
>>>>>>> 545fb3c (feat: 피드백 반영)

    override fun inputNumber(number: Int) {
        expression += number

<<<<<<< HEAD
        view.showCalculateExpression(expression.toString())
=======
        showCalculateExpression(expression);
>>>>>>> 545fb3c (feat: 피드백 반영)
    }

    override fun inputOperator(operatorData: Operator) {
        expression += operatorData

<<<<<<< HEAD
        view.showCalculateExpression(expression.toString())
=======
        showCalculateExpression(expression)
>>>>>>> 545fb3c (feat: 피드백 반영)
    }

    override fun removeLastIndexData() {
        expression = expression.removeLast()

<<<<<<< HEAD
        view.showCalculateExpression(expression.toString())
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

    override fun clickRecord(isShown: Boolean) {
        if (isShown) {
            view.hideRecord()
        } else {
            view.showRecord()
        }
    }

    override fun loadRecordData() {
        view.loadRecordList(recordList)
=======
        showCalculateExpression(expression)
    }

    override fun calculateInputValue() {

        if (!(expression.isValid())) {
            view.showCompletionOfExpressionDataMessage()
            return
        }

        showCalculateExpression(expression)
    }

    private fun showCalculateExpression(inputExpression: Expression) {
        if (inputExpression.isValid()) view.showCalculateExpression(inputExpression.toString())
>>>>>>> 545fb3c (feat: 피드백 반영)
    }

}