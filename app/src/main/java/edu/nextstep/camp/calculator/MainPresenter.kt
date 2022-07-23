package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.view.MemoryUIModel

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private var isMemoryViewDisplayed = true
    private var logs: List<MemoryUIModel> = arrayListOf()

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
            view.showExpressionMemoryView()
            view.setResultTextView(" ")
        }
        else {
            clearExpressions()
            isMemoryViewDisplayed = true
            view.hideExpressionMemoryView()
            view.setResultTextView(expression.toString())
        }
    }

    private fun saveExpression(expression: String, result: String) {
        val _logs = logs.toMutableList()
        _logs.add(MemoryUIModel(expression, "= $result"))
        logs = _logs
    }

    private fun clearExpressions() {
        val _logs = logs.toMutableList()
        _logs.clear()
        logs = _logs
    }

    override fun getItemList(): List<MemoryUIModel> = logs
}