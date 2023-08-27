package camp.nextstep.edu.domain

class ExpressionHandler {

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
    }

    fun deleteLast() {
        val deletedExpression = expression.trimEnd().dropLast(1)
        expression =
            if (deletedExpression.endsWith(" ")) deletedExpression.trimEnd()
            else deletedExpression
    }
}