package camp.nextstep.edu.domain.calculator


class ExpressionParser {

    fun parse(
        expression: String
    ): List<ExpressionItem> {
        require(expression.isNotEmpty()) { "공백은 입력할 수 없습니다." }

        val items = expression.trim().split(" ")
        require(isValidExpression(items)) { "유효하지 않은 표현식입니다." }

        return toExpressionItem(items)
    }

    private fun toExpressionItem(
        items: List<String>
    ): List<ExpressionItem> {
        val temp = mutableListOf<ExpressionItem>()
        items.forEach {
            temp.add(
                if (it.isNumeric())
                    Num(it.toInt())  // Numbers
                else
                    Operators.of(it) // Operators
            )
        }

        return temp
    }

    private fun isValidExpression(items: List<String>) =
        (items.size % 2 != 0) && items.first().isNumeric() && items.last().isNumeric()

    private fun String.isNumeric(): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return matches(regex)
    }
}
