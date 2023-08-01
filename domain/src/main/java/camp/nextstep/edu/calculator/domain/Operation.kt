package camp.nextstep.edu.calculator.domain

sealed class Operation(val operationText: String) {
    abstract fun evaluate(x: Int, y: Int): Int

    object Addition : Operation("+") {
        override fun evaluate(x: Int, y: Int): Int = x + y
    }

    object Subtraction : Operation("-") {
        override fun evaluate(x: Int, y: Int): Int = x - y
    }

    object Multiplication : Operation("*") {
        override fun evaluate(x: Int, y: Int): Int = x * y
    }

    object Division : Operation("/") {
        override fun evaluate(x: Int, y: Int): Int = x / y
    }

    companion object {
        fun findOperation(text: String): Operation? {
            val sealedSubclasses = Operation::class.sealedSubclasses.map { it.objectInstance }
            return sealedSubclasses.find { operations -> operations?.operationText == text }
        }
    }
}
