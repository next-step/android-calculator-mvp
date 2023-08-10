package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation

class Expression(private val inputTextConvertor: InputTextConvertor) {
    fun addExpression(expressionItems: ExpressionItems, addText: String): ExpressionItems {
        return runCatching {
            if (expressionItems.isEmpty()) addFirstExpression(expressionItems, addText)
            else addOperandOrOperationText(expressionItems, addText)
        }.getOrDefault(expressionItems)
    }

    private fun addFirstExpression(expressionItems: ExpressionItems, addText: String): ExpressionItems {
        val number = inputTextConvertor.getNumberText(addText)
        require(number != 0) { "first input text zero" }

        return expressionItems.addExpression(Operand(number))
    }

    private fun addOperandOrOperationText(expressionItems: ExpressionItems, addText: String): ExpressionItems {
        val expression = expressionItems.lastExpression()

        return if (expression is Operand) {
            addOperand(expressionItems, expression, addText)
        } else {
            addOperation(expressionItems, addText)
        }
    }

    private fun addOperand(expressionItems: ExpressionItems, operand: Operand, addText: String): ExpressionItems {
        val operation: Operation? = Operation.findOperation(addText)

        return if (operation != null) {
            expressionItems.addExpression(operation)
        } else {
            operand.addNumberText(inputTextConvertor.getNumberText(addText))
            expressionItems
        }
    }

    private fun addOperation(expressionItems: ExpressionItems, addText: String): ExpressionItems {
        val operation: Operation? = Operation.findOperation(addText)

        return if (operation != null) {
            expressionItems.setLastExpression(operation)
        } else {
            expressionItems.addExpression(Operand(inputTextConvertor.getNumberText(addText)))
        }
    }

    fun removeExpressionItem(expressionItems: ExpressionItems): ExpressionItems {
        return runCatching {
            val expression = expressionItems.lastExpression()

            return if (expression is Operand) {
                removeExpressionItem(expressionItems, expression)
            } else {
                expressionItems.removeLastExpression()
            }
        }.getOrDefault(expressionItems)
    }

    private fun removeExpressionItem(expressionItems: ExpressionItems, operand: Operand): ExpressionItems {
        return if (!operand.removeNumberText()) {
            expressionItems.removeLastExpression()
        } else {
            expressionItems
        }
    }
}
