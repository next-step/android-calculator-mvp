package camp.nextstep.edu.domain

class Validator {
    fun validate(expressions: List<String>) {
        if (hasNumberInOperatorIndex(expressions)
            || hasValidOperatorInNumberIndex(expressions)
            || hasInvalidValue(expressions)
        ) {
            throw IllegalArgumentException()
        }
    }

    private fun hasInvalidValue(expressions: List<String>) = expressions.any { it.isEmpty() }

    private fun hasNumberInOperatorIndex(expressions: List<String>): Boolean {
        return expressions
            .filterIndexed { index, _ ->
                (index + 1) % 2 == 1
            }
            .any {
                it.toLongOrNull() == null
            }
    }

    private fun hasValidOperatorInNumberIndex(expressions: List<String>): Boolean {
        return expressions
            .filterIndexed { index, _ ->
                (index + 1) % 2 == 0
            }
            .any {
                it.toLongOrNull() != null || isInvalidOperator(it)
            }
    }

    private fun isInvalidOperator(operator: String) = operator !in CalculationOperator.values().map { it.symbol }
}