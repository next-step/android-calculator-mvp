package camp.nextstep.edu.domain


data class Expression(private val exp: String = "") {

	fun getCompleteExpression(): String {
		require(exp.isNotBlank()) { EXP_IS_BLANK }
		check(exp.trim().last().isDigit()) { EXP_NOT_COMPLETE }

		return this.toString()
	}

	operator fun plus(operand: String): Expression {
		return this.copy(exp = exp.plus(operand))
	}

	operator fun plus(operator: Operator): Expression {
		if (exp.isEmpty()) {
			return this
		}

		return if (Operator.contains(exp.last().toString())) {
			this.copy(exp = exp.dropLast(1).plus(operator.op))
		} else {
			this.copy(exp = exp.plus(operator.op))
		}
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
			append(sb, char)
		}

		return sb.toString()
	}

	private fun append(sb: StringBuilder, char: Char) {
		if (Operator.contains(char.toString())) {
			sb.append(EXP_DELIMITER)
			sb.append(char)
			sb.append(EXP_DELIMITER)
		} else {
			sb.append(char)
		}
	}

	companion object {
		const val EXP_IS_BLANK = "expression is blank"
		const val EXP_NOT_COMPLETE = "expression is not complete"

		const val EXP_DELIMITER = " "
	}
}