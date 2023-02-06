package camp.nextstep.edu.domain.calculator


class Calculator {

    fun evaluate(expression: String?): Int {
        val parsed = ExpressionParser().parse(expression)
        return calculateActual(parsed)
    }

    private var pointer = 0
    private tailrec fun calculateActual(
        expressionItems: List<ExpressionItem>,
        accumulator: List<ExpressionItem> = listOf()
    ): Int = when {
        pointer >= expressionItems.size ->
            (accumulator.first() as Num).value
        accumulator.isEmpty() || accumulator.last() is Num -> {
            // 기호
            val head = expressionItems[pointer]
            pointer += 1
            calculateActual(expressionItems, accumulator + head)
        }
        else -> {
            val first = accumulator.first() as Num
            val second = expressionItems[pointer] as Num
            val symbol = accumulator.last() as Operators
            pointer += 1

            calculateActual(
                expressionItems,
                listOf(Num(symbol.calculate(first.value, second.value)))
            )
        }
    }
}
