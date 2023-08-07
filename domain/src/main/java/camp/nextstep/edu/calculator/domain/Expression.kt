package camp.nextstep.edu.calculator.domain

sealed class Expression {
    abstract fun getText(): String

    class OperandExpression(val operand: Operand) : Expression() {
        override fun getText(): String = operand.number.toString()
    }

    class OperationExpression(private val operation: Operation) : Expression() {
        override fun getText(): String = " ${operation.operationText} "
    }
}
