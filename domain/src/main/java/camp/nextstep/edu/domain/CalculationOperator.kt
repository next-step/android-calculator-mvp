package camp.nextstep.edu.domain

enum class CalculationOperator(val symbol: String) {
    PLUS("+") {
        override fun calculate(firstNumber: Long, secondNumber: Long) = firstNumber + secondNumber
    },
    MINUS("-") {
        override fun calculate(firstNumber: Long, secondNumber: Long) = firstNumber - secondNumber
    },
    DIVIDE("÷") {
        override fun calculate(firstNumber: Long, secondNumber: Long) = firstNumber / secondNumber
    },
    MULTIPLY("×") {
        override fun calculate(firstNumber: Long, secondNumber: Long) = firstNumber * secondNumber
    };

    abstract fun calculate(firstNumber: Long, secondNumber: Long): Long

    companion object {
        fun String.getCalculationResult(firstNumber: Long, secondNumber: Long): Long {
            return CalculationOperator.values()
                .find { it.symbol == this }
                ?.calculate(firstNumber, secondNumber)
                ?: throw IllegalArgumentException("Unknown Operator.")
        }
    }
}