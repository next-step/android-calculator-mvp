package camp.nextstep.edu.domain


data class Expression(private val exp: String = "") {

	fun getOrThrow(): String {
		require(exp.isNotBlank()) { EXP_IS_BLANK }
		check(exp.trim().last().isDigit()) { EXP_NOT_COMPLETE }

		return this.toString()
	}

	fun insertOperand(operand: String): Expression {
		return this.copy(exp = exp.plus(operand))
	}

	fun insertOperator(operator: Operator): Expression {
		if (exp.isEmpty()) {
			return this
		}

		return if (isOperator(exp.last())) {
			this.copy(exp = exp.dropLast(1).plus(operator.op))
		} else {
			this.copy(exp = exp.plus(operator.op))
		}
	}

	private fun isOperator(char: Char): Boolean {
		return Operator.values().find { operator ->
			operator.op == char.toString()
		} != null
	}

	fun delete(): Expression {
		if (exp.isEmpty()) {
			return this
		}

		return this.copy(exp = exp.dropLast(1))
	}

	override fun toString(): String {
		val sb = StringBuilder()

		exp.forEach { char ->
			if (isOperator(char)) {
				sb.append(EXP_DELIMITER)
				sb.append(char)
				sb.append(EXP_DELIMITER)
			} else {
				sb.append(char)
			}
		}

		return sb.toString()
	}

	companion object {
		const val EXP_IS_BLANK = "expression is blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"

		const val EXP_DELIMITER = " "
	}
}