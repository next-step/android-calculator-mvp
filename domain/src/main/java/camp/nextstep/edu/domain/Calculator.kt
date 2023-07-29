package camp.nextstep.edu.domain

class Calculator {

    fun evaluate(expression: String): Long {
        val expressions = ExpressionParser.parse(expression)
        val validator = Validator()
        validator.validate(expressions)

        var result = expressions.first().toLong()

        for (i in 1 until  expressions.size step 2) {
            val operator = expressions[i]
            val secondNumber = expressions[i + 1].toLong()
            result = operator.getEvaluationResult(result, secondNumber)
        }

        return result
    }

    private fun String.getEvaluationResult(firstNumber: Long, secondNumber: Long): Long {
        return when (this) {
            "+" -> plus(firstNumber, secondNumber)
            "-" -> minus(firstNumber, secondNumber)
            "÷" -> divide(firstNumber, secondNumber)
            "×" -> multiply(firstNumber, secondNumber)
            else -> throw IllegalArgumentException()
        }
    }

    private fun plus(firstNumber: Long, secondNumber: Long) = firstNumber + secondNumber

    private fun minus(firstNumber: Long, secondNumber: Long) = firstNumber - secondNumber

    private fun divide(firstNumber: Long, secondNumber: Long) = firstNumber / secondNumber

    private fun multiply(firstNumber: Long, secondNumber: Long) = firstNumber * secondNumber
}