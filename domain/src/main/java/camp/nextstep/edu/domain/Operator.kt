package camp.nextstep.edu.domain

enum class Operator(val op: String) {
	PLUS("+"), MINUS("-"), MUL("*"), DIV("/");

	companion object {
		const val INVALID_OPERATOR = "invalid operator"

		fun getOrThrow(op: String): Operator {
			return Operator.values().find { operator ->
				operator.op == op
			} ?: throw IllegalArgumentException(INVALID_OPERATOR)
		}
	}
}