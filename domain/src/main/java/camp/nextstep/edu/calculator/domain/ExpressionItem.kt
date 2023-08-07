package camp.nextstep.edu.calculator.domain

sealed class ExpressionItem {
    abstract fun getText(): String

    class OperandExpression(val operand: Operand) : ExpressionItem() {
        override fun getText(): String = operand.number.toString()
    }

    class OperationExpression(private val operation: Operation) : ExpressionItem() {
        override fun getText(): String = " ${operation.operationText} "
    }
}
