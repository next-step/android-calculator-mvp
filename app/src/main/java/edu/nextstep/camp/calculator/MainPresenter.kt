package edu.nextstep.camp.calculator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

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
        expression = Expression.EMPTY + result
        view.setResultTextView(result.toString())
    }
}