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
		var result = inputs.firstOrNull()?.toIntOrNull() ?: throw IllegalStateException()

		for (i in 1 until inputs.size step 2) {
			val op = inputs[i]
			val number = inputs[i + 1].toIntOrNull() ?: throw IllegalStateException()
			val operator = Operator.values().find { operator ->
				operator.op == op
			} ?: throw IllegalArgumentException()

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
