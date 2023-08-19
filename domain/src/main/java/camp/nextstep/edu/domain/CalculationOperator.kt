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
        fun getCalculationResult(
            firstNumber: Long,
            secondNumber: Long,
            operator: CalculationOperator
        ): Long {
            return CalculationOperator.values()
                .find { it.symbol == operator.symbol }
                ?.calculate(firstNumber, secondNumber)
                ?: throw IllegalArgumentException("Unknown Operator.")
        }
    }
}