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

        fun getOperator(operator: String) =
            CalculationOperator.values().firstOrNull { it.symbol == operator }
                ?: throw IllegalArgumentException("Unknown Operator.")

        fun getEvaluation(
            firstNumber: Long,
            secondNumber: Long,
            operator: CalculationOperator
        ) = getOperator(operator.symbol).calculate(firstNumber, secondNumber)
    }
}