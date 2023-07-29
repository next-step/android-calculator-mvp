package camp.nextstep.edu.calculator.domain

@JvmInline
value class ArithmeticExpression(private val expression: String) {

    init {
        require(isValidExpression()) { "Not valid expression" }
    }

    fun getExpressionItemList() = expression.split(EXPRESSION_DELIMITER)

    fun getDigitPositionedList(expressionItemsList: List<String>) =
        expressionItemsList
            .filterIndexed { index, _ -> index % 2 == 0 }

    fun getOperatorPositionedList(expressionItemsList: List<String>) =
        expressionItemsList
            .filterIndexed { index, _ -> index % 2 == 1 }

    private fun isValidExpression(): Boolean {
        val expressionItemsList = getExpressionItemList()

        return when {
            !isValidItemCount(expressionItemsList) -> false
            !isValidItemSequence(expressionItemsList) -> false
            else -> true
        }
    }

    private fun isValidItemCount(expressionItemsList: List<String>) =
        expressionItemsList.count() % 2 == 1

    private fun isValidItemSequence(expressionItemsList: List<String>) =
        isValidOperatorSequence(expressionItemsList) &&
                isValidDigitSequence(expressionItemsList)

    private fun isValidOperatorSequence(expressionItemsList: List<String>) =
        getOperatorPositionedList(expressionItemsList)
            .all { ArithmeticOperator.isArithmeticOperator(it) }

    private fun isValidDigitSequence(expressionItemsList: List<String>) =
        getDigitPositionedList(expressionItemsList)
            .all { it.isDigit() }

    private fun String.isDigit() = this.toIntOrNull() != null

    companion object {
        private const val EXPRESSION_DELIMITER = " "
    }
}
