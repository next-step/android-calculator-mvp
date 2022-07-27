package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.view.MemoryUIModel

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
) : MainContract.Presenter {
    private var isMemoryViewDisplayed = true
    private val logs = mutableListOf<MemoryUIModel>()

    override fun onClickNumberButton(number: Int) {
        expression += number
        view.setResultTextView(expression.toString())
    }

    override fun onClickOperandButton(operator: String) {
        expression += Operator.of(operator) ?: throw IllegalArgumentException("it is not operator")
        view.setResultTextView(expression.toString())
    }

    override fun onClickDeleteButton() {
        expression.removeLast()
        view.setResultTextView(expression.toString())
    }

    override fun onClickEqualButton() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showToastIncompleteExpression()
            return
        }
        saveExpression(expression.toString(), result.toString())
        expression = Expression.EMPTY + result
        view.setResultTextView(result.toString())
    }

    override fun onClickMemoryButton() {
        if (isMemoryViewDisplayed) {
            isMemoryViewDisplayed = false
            view.showExpressionMemoryView(logs)
            view.setResultTextView(" ")
        }
        else {
            isMemoryViewDisplayed = true
            view.hideExpressionMemoryView()
            view.setResultTextView(expression.toString())
        }
    }

    private fun saveExpression(expression: String, result: String) {
        logs.add(MemoryUIModel(logs.size, expression, "= $result"))
    }
}