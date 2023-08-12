package camp.nextstep.edu.calculator.domain

sealed class ExpressionItem {
    abstract fun getText(): String

    class Operand(val number: Int = 0) : ExpressionItem() {
        override fun getText(): String = number.toString()
    }

    sealed class Operation(val operationText: String) : ExpressionItem() {
        abstract fun evaluate(x: Int, y: Int): Int

        object Addition : Operation("+") {
            override fun evaluate(x: Int, y: Int): Int = x + y
            override fun getText(): String = " $operationText "
        }

        object Subtraction : Operation("-") {
            override fun evaluate(x: Int, y: Int): Int = x - y
            override fun getText(): String = " $operationText "
        }

        object Multiplication : Operation("*") {
            override fun evaluate(x: Int, y: Int): Int = x * y
            override fun getText(): String = " $operationText "
        }

        object Division : Operation("/") {
            override fun evaluate(x: Int, y: Int): Int = x / y
            override fun getText(): String = " $operationText "
        }

        companion object {
            fun findOperation(text: String): Operation? {
                val sealedSubclasses = Operation::class.sealedSubclasses.map { it.objectInstance }
                return sealedSubclasses.find { operations -> operations?.operationText == text }
            }
        }
    }
}
