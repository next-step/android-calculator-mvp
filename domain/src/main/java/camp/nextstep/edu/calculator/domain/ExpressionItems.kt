package camp.nextstep.edu.calculator.domain

class ExpressionItems {
    private var expressionStack: List<ExpressionItem> = listOf()

    fun addExpression(expression: ExpressionItem) {
        ArrayDeque(expressionStack).apply {
            add(expression)
            expressionStack = toList()
        }
    }

    fun setLastExpression(expression: ExpressionItem) {
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

    fun lastExpression(): ExpressionItem = expressionStack.last()

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
