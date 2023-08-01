package camp.nextstep.edu.domain

class Expression {

	private val expStringBuilder = StringBuilder()

	fun getOrThrow(): String {
		val expression = expStringBuilder.toString()

		require(expression.isNotBlank()) { EXP_IS_BLANK }
		check(expression.trim().last().isDigit()) { EXP_NOT_COMPLETE }

		return expression
	}

	fun insertOperand(operand: String) {
		expStringBuilder.append(operand)
	}

	fun insertOperator(operator: String) {
		if (expStringBuilder.isEmpty()) {
			return
		}

		expStringBuilder.append(EXP_DELIMITER)
		expStringBuilder.append(operator)
		expStringBuilder.append(EXP_DELIMITER)
	}

	fun delete() {
		if (expStringBuilder.isEmpty()) {
			return
		}

		if (expStringBuilder.last() == EXP_DELIMITER) {
			deleteWithDelimiter()
		} else {
			expStringBuilder.deleteCharAt(expStringBuilder.lastIndex)
		}
	}

	private fun deleteWithDelimiter() {
		repeat(3) {
			expStringBuilder.deleteCharAt(expStringBuilder.lastIndex)
		}
	}

	fun clear() {
		expStringBuilder.clear()
	}

	companion object {
		const val EXP_IS_BLANK = "expression is blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"

		const val EXP_DELIMITER = ' '
	}
}