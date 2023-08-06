package camp.nextstep.edu.domain

class Expression(exp: String = "") {

	private val stringBuilder: StringBuilder = StringBuilder(exp)

	fun getOrThrow(): String {
		val expression = this.toString()

		require(expression.isNotBlank()) { EXP_IS_BLANK }
		check(expression.trim().last().isDigit()) { EXP_NOT_COMPLETE }

		return expression
	}

	fun insertOperand(operand: String) {
		stringBuilder.append(operand)
	}

	fun insertOperator(operator: Operator) {
		if (stringBuilder.isEmpty()) {
			return
		}

		if (stringBuilder.last() == EXP_DELIMITER) {
			deleteWithDelimiter()
		}

		stringBuilder.append(EXP_DELIMITER)
		stringBuilder.append(operator.op)
		stringBuilder.append(EXP_DELIMITER)
	}

	fun delete() {
		if (stringBuilder.isEmpty()) {
			return
		}

		if (stringBuilder.last() == EXP_DELIMITER) {
			deleteWithDelimiter()
		} else {
			stringBuilder.deleteCharAt(stringBuilder.lastIndex)
		}
	}

	private fun deleteWithDelimiter() {
		repeat(TRIPLE_ITERATIONS) {
			stringBuilder.deleteCharAt(stringBuilder.lastIndex)
		}
	}

	override fun toString(): String {
		return stringBuilder.toString()
	}

	companion object {
		const val EXP_IS_BLANK = "expression is blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"

		const val EXP_DELIMITER = ' '
		const val TRIPLE_ITERATIONS = 3
	}
}