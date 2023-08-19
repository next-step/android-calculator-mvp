package camp.nextstep.edu.domain

import camp.nextstep.edu.domain.CalculationOperator.Companion.getCalculationResult

class Evaluator {

    fun evaluate(expression: String): Long {
        val expressions = ExpressionParser.parse(expression)
        val validator = Validator()
        validator.validate(expressions)

        var result = expressions.first().toLong()

        for (i in 1 until  expressions.size step 2) {
            val operator = CalculationOperator.valueOf(expressions[i])
            val secondNumber = expressions[i + 1].toLong()

            result = getCalculationResult(
                firstNumber = result,
                secondNumber = secondNumber,
                operator = operator,
            )
        }

        return result
    }
}