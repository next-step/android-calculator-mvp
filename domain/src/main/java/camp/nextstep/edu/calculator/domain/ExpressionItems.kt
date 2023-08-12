package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation
import kotlin.math.abs

class ExpressionItems(private val expressionStack: List<ExpressionItem>) {

    fun addExpression(expression: ExpressionItem): ExpressionItems {
        return if (expressionStack.isEmpty()) {
            addFirstExpression(expression)
        } else {
            addExpressionItem(expression)
        }
    }

    private fun addFirstExpression(expression: ExpressionItem): ExpressionItems {
        return if (expression is Operand) {
            ExpressionItems(expressionStack + expression)
        } else {
            this
        }
    }

    private fun addExpressionItem(expression: ExpressionItem): ExpressionItems {
        return if (expression is Operand) {
            addOperandExpressionItem(expression)
        } else {
            addOperationExpressionItem(expression)
        }
    }

    private fun addOperandExpressionItem(expression: Operand): ExpressionItems {
        val lastItem = expressionStack.last()

        return if (lastItem is Operand) {
            setLastExpression(Operand("${lastItem.number}${expression.number}".toInt()))
        } else {
            ExpressionItems(expressionStack + expression)
        }
    }

    private fun addOperationExpressionItem(expression: ExpressionItem): ExpressionItems {
        val lastItem = expressionStack.last()

        return if (lastItem is Operation) {
            setLastExpression(expression)
        } else {
            ExpressionItems(expressionStack + expression)
        }
    }

    private fun setLastExpression(expression: ExpressionItem): ExpressionItems {
        return ExpressionItems(
            ArrayDeque(expressionStack).apply {
                set(lastIndex, expression)
            }.toList()
        )
    }

    fun removeLastExpression(): ExpressionItems {
        val lastItem = expressionStack.lastOrNull() ?: return this

        return if (lastItem is Operand) {
            removeOperandExpression(lastItem)
        } else {
            removeExpression()
        }
    }

    private fun removeOperandExpression(operand: Operand): ExpressionItems {
        return if (abs(operand.number) > 10) {
            setLastExpression(
                Operand(operand.number.toString().let { it.substring(0, it.lastIndex).toInt() })
            )
        } else {
            removeExpression()
        }
    }

    private fun removeExpression(): ExpressionItems {
        return ExpressionItems(
            ArrayDeque(expressionStack).apply {
                removeLastOrNull()
            }.toList()
        )
    }

    fun getText(): String {
        return StringBuilder().apply {
            for (expression in expressionStack) {
                append(expression.getText())
            }
        }.toString()
    }
}

operator fun ExpressionItems.plus(expression: ExpressionItem): ExpressionItems {
    return addExpression(expression)
}
