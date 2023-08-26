package camp.nextstep.edu.domain

class ExpressionHandler(
    private val onExpressionChanged: (expression: String) -> Unit
) {

    var expression = ""
        private set

    fun addInputValue(inputValue: String) {
        val isDigitInputValue = inputValue.trim().first().isDigit()
        val isExpressionEmpty = expression == "0" || expression.isEmpty()
        val lastExpression = expression.trimEnd().lastOrNull()

        if (isExpressionEmpty && !isDigitInputValue) return
        if (lastExpression?.isDigit() == false && !isDigitInputValue) {
            expression = expression.trimEnd().dropLast(1).trimEnd() + inputValue
        } else {
            expression += inputValue
        }

        onExpressionChanged(expression)
    }

    fun deleteLast() {
        val deletedExpression = expression.trimEnd().dropLast(1)
        expression =
            if (deletedExpression.endsWith(" ")) deletedExpression.trimEnd()
            else deletedExpression

        onExpressionChanged(expression)
    }

    fun setEvaluationResult(result: String) {
        onExpressionChanged(result)
    }
}