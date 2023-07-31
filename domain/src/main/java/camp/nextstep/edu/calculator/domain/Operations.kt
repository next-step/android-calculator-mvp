package camp.nextstep.edu.calculator.domain

sealed class Operations(val operationText: String) {
    abstract fun evaluate(x: Int, y: Int): Int

    object Addition : Operations("+") {
        override fun evaluate(x: Int, y: Int): Int = x + y
    }

    object Subtraction : Operations("-") {
        override fun evaluate(x: Int, y: Int): Int = x - y
    }

    object Multiplication : Operations("*") {
        override fun evaluate(x: Int, y: Int): Int = x * y
    }

    object Division : Operations("/") {
        override fun evaluate(x: Int, y: Int): Int = x / y
    }

    companion object {
        fun findOperation(text: String): Operations? {
            val sealedSubclasses = Operations::class.sealedSubclasses.map { it.objectInstance }
            return sealedSubclasses.find { operations -> operations?.operationText == text }
        }
    }
}
