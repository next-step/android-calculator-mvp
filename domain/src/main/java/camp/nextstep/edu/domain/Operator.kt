package camp.nextstep.edu.domain

enum class Operator(val op: String) {


	PLUS("+") {
		override fun evaluate(num1: Int, num2: Int): Int {
			return num1 + num2
		}
	},
	MINUS("-") {
		override fun evaluate(num1: Int, num2: Int): Int {
			return num1 - num2
		}
	}, MUL("*") {
		override fun evaluate(num1: Int, num2: Int): Int {
			return num1 * num2
		}
	}, DIV("/") {
		override fun evaluate(num1: Int, num2: Int): Int {
			return num1 / num2
		}
	};

	abstract fun evaluate(num1: Int, num2: Int): Int

	companion object {
		const val INVALID_OPERATOR = "invalid operator"

		private fun get(op: String): Operator? {
			return Operator.values().find { operator ->
				operator.op == op
			}
		}

		fun getOrThrow(op: String): Operator {
			return get(op) ?: throw IllegalArgumentException(INVALID_OPERATOR)
		}

		fun contains(op: String): Boolean {
			return get(op) != null
		}
	}
}