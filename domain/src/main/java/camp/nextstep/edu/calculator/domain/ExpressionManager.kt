package camp.nextstep.edu.calculator.domain

class ExpressionManager {
    private var expressionStack: List<Expression> = listOf()

    fun addExpression(expression: Expression) {
        ArrayDeque(expressionStack).apply {
            add(expression)
            expressionStack = toList()
        }
    }

    fun setLastExpression(expression: Expression) {
        ArrayDeque(expressionStack).apply {
            set(lastIndex, expression)
            expressionStack = toList()
        }
    }

    fun removeLastExpression() {
        ArrayDeque(expressionStack).apply {
            removeLast()
            expressionStack = toList()
        }
    }

    fun lastExpression(): Expression = expressionStack.last()

    fun clear() {
        expressionStack = emptyList()
    }

    fun isEmpty(): Boolean = expressionStack.lastOrNull() == null

    fun getText(): String {
        return StringBuilder().apply {
            for (expression in expressionStack) {
                append(expression.getText())
            }
        }.toString()
    }
}
