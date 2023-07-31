package camp.nextstep.edu.domain

class Calculator {

	fun calculate(expression: String?): Int {
		if (expression.isNullOrBlank()) {
			throw IllegalArgumentException(EXP_NULL_OR_BLANK)
		}

		if (expression.trim().last().isDigit().not()) {
			throw IllegalStateException(EXP_NOT_COMPLETE)
		}

		val inputs = expression.trim().split(" ")
		var result = inputs.first().toIntOrThrow()

		for (i in 1 until inputs.size step 2) {
			val operator = Operator.getOrThrow(inputs[i])
			val number = inputs[i + 1].toIntOrThrow()

			when (operator) {
				Operator.PLUS -> result += number
				Operator.MINUS -> result -= number
				Operator.MUL -> result *= number
				Operator.DIV -> result /= number
			}
		}

		return result
	}

	companion object {
		const val EXP_NULL_OR_BLANK = "expression is null or blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"
	}
}
