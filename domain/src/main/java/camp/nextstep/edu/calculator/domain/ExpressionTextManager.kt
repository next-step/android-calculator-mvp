package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.Expression.OperandExpression
import camp.nextstep.edu.calculator.domain.Expression.OperationExpression

class ExpressionTextManager(
    private val inputTextConvertor: InputTextConvertor,
    private val expressionManager: ExpressionManager
) {
    fun addText(addText: String): String {
        val defaultText = expressionManager.getText()
        return runCatching {
            if (expressionManager.isEmpty()) {
                addFirstExpression(addText)
            } else {
                addNumberOrOperationText(addText)
            }

            expressionManager.getText()
        }.getOrDefault(defaultText)
    }

    private fun addFirstExpression(addText: String) {
        val number = inputTextConvertor.getNumberText(addText)
        require(number != 0) { "first input text zero" }

        expressionManager.addExpression(OperandExpression(Operand(number)))
    }

    private fun addNumberOrOperationText(addText: String) {
        val expression = expressionManager.lastExpression()

        if (expression is OperandExpression) {
            addNumberText(expression, addText)
        } else {
            addOperationText(addText)
        }
    }

    private fun addNumberText(expression: OperandExpression, addText: String) {
        val operation: Operation? = Operation.findOperation(addText)

        if (operation != null) {
            val operationExpression = OperationExpression(operation)
            expressionManager.addExpression(operationExpression)
        } else {
            expression.operand.addNumberText(inputTextConvertor.getNumberText(addText))
        }
    }

    private fun addOperationText(addText: String) {
        val operation: Operation? = Operation.findOperation(addText)

        if (operation != null) {
            val operationExpression = OperationExpression(operation)
            expressionManager.setLastExpression(operationExpression)
        } else {
            expressionManager.addExpression(
                OperandExpression(Operand(inputTextConvertor.getNumberText(addText)))
            )
        }
    }

    fun removeText(): String {
        return runCatching {
            val expression = expressionManager.lastExpression()

            if (expression is OperandExpression) {
                removeExpressionText(expression)
            } else {
                expressionManager.removeLastExpression()
            }

            expressionManager.getText()
        }.getOrDefault(expressionManager.getText())
    }

    private fun removeExpressionText(expression: OperandExpression) {
        if (!expression.operand.removeNumberText()) {
            expressionManager.removeLastExpression()
        }
    }

    fun calculateText(): String {
        val expressionText = expressionManager.getText()
        return runCatching {
            Calculator(inputTextConvertor).evaluate(expressionText).toString().apply {
                expressionManager.clear()
                addText(this)
            }
        }.getOrDefault(expressionText)
    }
}
