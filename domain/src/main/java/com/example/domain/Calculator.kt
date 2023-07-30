package com.example.domain

class Calculator {

	fun calculate(expression: String?): Int {
		if (expression.isNullOrBlank()) {
			throw IllegalArgumentException()
		}

		if (expression.last().isDigit().not()) {
			throw IllegalStateException()
		}

		val inputs = expression.split(" ")
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
}
