package camp.nextstep.edu.calculator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private val calculator = Calculator()
    private val expression = Expression(calculator)

    override fun updateExpressions(input: String) {
        expression.updateExpressions(input)
        view.showExpression(expression.text)
    }

    override fun deleteLast() {
        expression.deleteLast()
        view.showExpression(expression.text)
    }

    override fun calculateAndReset() {
        runCatching {
            expression.calculateAndReset()
        }.onSuccess {
            view.showExpression(expression.text)
        }.onFailure { exception ->
            if (exception is IllegalArgumentException) {
                view.showIncompleteExpressionError()
            }
        }
    }
}