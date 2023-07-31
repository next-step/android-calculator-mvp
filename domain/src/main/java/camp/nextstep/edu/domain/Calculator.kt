package camp.nextstep.edu.domain

class Calculator {

	fun calculate(expression: String?): Int {
		requireNotNull(expression) { EXP_IS_NULL}
		require(expression.isNotBlank()) { EXP_IS_BLANK }

		if (expression.trim().last().isDigit().not()) {
			throw IllegalStateException(EXP_NOT_COMPLETE)
		}

		val inputs = expression.trim().split(" ")
		var result = inputs.first().toIntOrThrow()

		for (i in 1 until inputs.size step 2) {
			val operator = Operator.getOrThrow(inputs[i])
			val number = inputs[i + 1].toIntOrThrow()

			result = operator.evaluate(result, number)
		}

		return result
	}

	companion object {
		const val EXP_IS_NULL = "expression is null"
		const val EXP_IS_BLANK = "expression is blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"
	}
}
