package camp.nextstep.edu.domain.calculator


class ExpressionParser {

    private val numericRegex by lazy { "-?[0-9]+(\\.[0-9]+)?".toRegex() }


    fun parse(
        expression: String
    ): List<ExpressionItem> {
        require(
            expression.isNotEmpty() &&
                    expression.isNotBlank()
        ) { "공백은 입력할 수 없습니다." }

        val items = expression.trim().split(" ")
        require(isValidExpression(items)) { "유효하지 않은 표현식입니다." }

        return toExpressionItem(items)
    }

    private fun toExpressionItem(
        items: List<String>
    ): List<ExpressionItem> {
        val expressionItems = mutableListOf<ExpressionItem>()
        items.forEach {
            expressionItems.add(
                if (it.isNumeric()) Num(it.toInt())
                else Operators.of(it)
            )
        }

        return expressionItems
    }

    private fun isValidExpression(items: List<String>) =
        (items.size % 2 != 0) && items.first().isNumeric() && items.last().isNumeric()

    private fun String.isNumeric() = matches(numericRegex)
}
