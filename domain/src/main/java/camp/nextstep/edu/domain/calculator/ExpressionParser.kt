package camp.nextstep.edu.domain.calculator


class ExpressionParser {

    private val numericRegex by lazy { "-?[0-9]+(\\.[0-9]+)?".toRegex() }


    fun parse(
        expression: String?
    ): List<ExpressionItem> {
        requireNotNull(expression) { "null 은 입력할 수 없습니다." }
        require(expression.isNotBlank()) { "공백은 입력할 수 없습니다." }

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

    private fun isValidExpression(items: List<String>): Boolean {
        if (items.size % 2 == 0) return false
        if (items.first().isNumeric().not()) return false
        if (items.last().isNumeric().not()) return false

        items.reduce { acc, s ->
            if (acc.isNumeric() && s.isNumeric())
                return false

            if (!acc.isNumeric() && !s.isNumeric())
                return false

            s
        }

        return true
    }

    private fun String.isNumeric() = matches(numericRegex)
}
