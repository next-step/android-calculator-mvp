package camp.nextstep.edu.calculator.domain

sealed class Expression {
    abstract fun getText(): String

    class NumberExpression(private var number: Int) : Expression() {
        override fun getText(): String = number.toString()

        fun addNumberText(addNumber: Int) {
            number = "$number$addNumber".toInt()
        }

        fun removeNumberText(): Boolean {
            return runCatching {
                number = getText().run {
                    subSequence(0, length - 1).toString().toInt()
                }
                true
            }.getOrDefault(false)
        }
    }

    class OperationExpression(private val operation: Operation) : Expression() {
        override fun getText(): String = " ${operation.operationText} "
    }
}
