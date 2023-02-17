package camp.nextstep.edu.calculator

class CalculatorPresenter(private val view: CalculatorContract.View) : CalculatorContract.Presenter {

    private val calculator = Calculator()
    private val expressions: StringBuilder = StringBuilder()

    override fun appendExpression(newText: String) {
        val expressionResult = calculator.displayCalculation(expressions.toString(), newText)
        view.showExpressions(expressionResult)
        expressions.clear().append(expressionResult)
    }

    override fun removeExpression() {
        if (expressions.isNotBlank()) {
            val expressionResult = calculator.clearCalculation(expressions.toString())
            view.showExpressions(expressionResult)
            expressions.clear().append(expressionResult)
        }
    }

    override fun calculate() {
        runCatching {
            calculator.evaluate(expressions.toString())
        }.onSuccess {
            val expressionResult = "$expressions = $it"
            view.showExpressions(expressionResult)
            expressions.clear().append(expressionResult)
        }.onFailure {
            view.inCompleteExpressionNotice()
        }
    }
}
