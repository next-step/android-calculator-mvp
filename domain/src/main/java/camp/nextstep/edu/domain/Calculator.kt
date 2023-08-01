package camp.nextstep.edu.domain

class Calculator {

	fun calculate(expression: String): Int {
		val inputs = parseExpression(expression)
		return calculate(inputs)
	}

	private fun parseExpression(expression: String): List<String> {
		return expression.trim().split(" ")
	}

	private fun calculate(inputs: List<String>): Int {
		var result = inputs.first().toIntOrThrow()

		for (i in 1 until inputs.size step 2) {
			val operator = Operator.getOrThrow(inputs[i])
			val number = inputs[i + 1].toIntOrThrow()

			result = operator.evaluate(result, number)
		}

		return result
	}
}
