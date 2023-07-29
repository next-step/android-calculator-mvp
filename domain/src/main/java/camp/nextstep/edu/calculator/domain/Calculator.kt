package camp.nextstep.edu.calculator.domain

class Calculator {

    fun calculate(expression: ArithmeticExpression): Int {
        val expressionItemList = expression.getExpressionItemList()
        val digitQueue = ArrayDeque<Int>().apply {
            addAll(
                expression.getDigitPositionedList(expressionItemList).map { it.toInt() }
            )
        }
        val operatorQueue = ArrayDeque<String>().apply {
            addAll(expression.getOperatorPositionedList(expressionItemList))
        }

        var result = digitQueue.removeFirst()
        while (digitQueue.isNotEmpty() && operatorQueue.isNotEmpty()) {
            result = operate(operatorQueue.removeFirst(), result, digitQueue.removeFirst())
        }

        return result
    }

    private fun operate(operator: String, operand1: Int, operand2: Int): Int =
        when (operator) {
            ArithmeticOperator.PLUS.value -> ArithmeticOperator.PLUS(operand1, operand2)
            ArithmeticOperator.MINUS.value -> ArithmeticOperator.MINUS(operand1, operand2)
            ArithmeticOperator.MULTIPLY.value -> ArithmeticOperator.MULTIPLY(operand1, operand2)
            ArithmeticOperator.DIVIDE.value -> ArithmeticOperator.DIVIDE(operand1, operand2)
            else -> operand1
        }
}
