package camp.nextstep.edu.calculator.domain

class ExpressionManager {
    private val expressionStack: ArrayDeque<Expression> = ArrayDeque()

    fun addExpression(expression: Expression) = expressionStack.add(expression)

    fun setLastExpression(expression: Expression) {
        expressionStack.apply { set(lastIndex, expression) }
    }

    fun removeLastExpression(): Expression = expressionStack.removeLast()

    fun lastExpression(): Expression = expressionStack.last()

    fun clear() = expressionStack.clear()

    fun isEmpty(): Boolean = expressionStack.lastOrNull() == null

    fun getText(): String {
        return StringBuilder().apply {
            for (expression in expressionStack) {
                append(expression.getText())
            }
        }.toString()
    }
}
