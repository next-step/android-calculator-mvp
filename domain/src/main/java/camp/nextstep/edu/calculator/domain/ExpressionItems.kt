package camp.nextstep.edu.calculator.domain

class ExpressionItems(private val expressionStack: List<ExpressionItem>) {

    fun addExpression(expression: ExpressionItem): ExpressionItems {
        return ExpressionItems(expressionStack + expression)
    }

    fun setLastExpression(expression: ExpressionItem): ExpressionItems {
        return ExpressionItems(
            ArrayDeque(expressionStack).apply {
                set(lastIndex, expression)
            }.toList()
        )
    }

    fun removeLastExpression(): ExpressionItems {
        return ExpressionItems(
            ArrayDeque(expressionStack).apply {
                removeLast()
            }.toList()
        )
    }

    fun lastExpression(): ExpressionItem = expressionStack.last()

    fun isEmpty(): Boolean = expressionStack.lastOrNull() == null

    fun getText(): String {
        return StringBuilder().apply {
            for (expression in expressionStack) {
                append(expression.getText())
            }
        }.toString()
    }
}
