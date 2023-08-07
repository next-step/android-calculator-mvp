package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.OperandExpression
import camp.nextstep.edu.calculator.domain.ExpressionItem.OperationExpression

class Expression(
    private val inputTextConvertor: InputTextConvertor,
    private val expressionItems: ExpressionItems
) {
    fun addExpression(addText: String): String {
        val defaultText = expressionItems.getText()

        return runCatching {
            if (expressionItems.isEmpty()) addFirstExpression(addText)
            else addOperandOrOperationText(addText)

            expressionItems.getText()
        }.getOrDefault(defaultText)
    }

    private fun addFirstExpression(addText: String) {
        val number = inputTextConvertor.getNumberText(addText)
        require(number != 0) { "first input text zero" }

        expressionItems.addExpression(OperandExpression(Operand(number)))
    }

    private fun addOperandOrOperationText(addText: String) {
        val expression = expressionItems.lastExpression()

        if (expression is OperandExpression) {
            addOperand(expression, addText)
        } else {
            addOperation(addText)
        }
    }

    private fun addOperand(expression: OperandExpression, addText: String) {
        val operation: Operation? = Operation.findOperation(addText)

        if (operation != null) {
            val operationExpression = OperationExpression(operation)
            expressionItems.addExpression(operationExpression)
        } else {
            expression.operand.addNumberText(inputTextConvertor.getNumberText(addText))
        }
    }

    private fun addOperation(addText: String) {
        val operation: Operation? = Operation.findOperation(addText)

        if (operation != null) {
            val operationExpression = OperationExpression(operation)
            expressionItems.setLastExpression(operationExpression)
        } else {
            expressionItems.addExpression(
                OperandExpression(Operand(inputTextConvertor.getNumberText(addText)))
            )
        }
    }

    fun removeExpressionItem(): String {
        return runCatching {
            val expression = expressionItems.lastExpression()

            if (expression is OperandExpression) {
                removeExpressionItem(expression)
            } else {
                expressionItems.removeLastExpression()
            }

            expressionItems.getText()
        }.getOrDefault(expressionItems.getText())
    }

    private fun removeExpressionItem(expression: OperandExpression) {
        if (!expression.operand.removeNumberText()) {
            expressionItems.removeLastExpression()
        }
    }

    fun calculate(): String {
        val expressionText = expressionItems.getText()
        return runCatching {
            Calculator(inputTextConvertor).evaluate(expressionText).toString().apply {
                expressionItems.clear()
                addExpression(this)
            }
        }.getOrDefault(expressionText)
    }
}
