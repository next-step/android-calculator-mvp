package camp.nextstep.edu.calculator.domain

sealed class ExpressionItem {
    abstract fun getText(): String

    class Operand(private var number: Int = 0) : ExpressionItem() {
        override fun getText(): String = number.toString()

        fun addNumberText(addNumber: Int) {
            number = runCatching {
                "$number$addNumber".toInt()
            }.getOrDefault(number)
        }

        fun removeNumberText(): Boolean {
            return runCatching {
                number = number.toString().let {
                    it.subSequence(0, it.length - 1).toString().toInt()
                }
                true
            }.getOrDefault(false)
        }
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
