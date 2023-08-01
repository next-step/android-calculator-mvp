package camp.nextstep.edu.calculator.domain

enum class Operator(val operator: String) {
    PLUS("+") {
        override fun calculate(preNumber: String, postNumber: String): String {
            return (preNumber.toInt() + postNumber.toInt()).toString()
        }
    },
    MINUS("-") {
        override fun calculate(preNumber: String, postNumber: String): String {
            return (preNumber.toInt() - postNumber.toInt()).toString()
        }
    },
    TIMES("*") {
        override fun calculate(preNumber: String, postNumber: String): String {
            return (preNumber.toInt() * postNumber.toInt()).toString()
        }
    },
    DIVIDE("/") {
        override fun calculate(preNumber: String, postNumber: String): String {
            return (preNumber.toInt() / postNumber.toInt()).toString()
        }
    };

    abstract fun calculate(preNumber: String, postNumber: String): String

    companion object {

        fun getOperator(operatorInput: String): Operator {
            val operator = findOperator(operatorInput)
            requireNotNull(operator) {
                "Cannot find operator"
            }
            return operator
        }
        private fun findOperator(operator: String): Operator? = values().firstOrNull { it.operator == operator }
    }
}
