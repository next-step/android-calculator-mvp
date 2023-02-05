package camp.nextstep.edu.domain.calculator


class Calculator {

    fun evaluate(expression: String): Int {
        val parsed = ExpressionParser().parse(expression)
        return calculateActual(parsed)
    }

    private tailrec fun calculateActual(
        expressionItems: List<ExpressionItem>,
        acc: List<ExpressionItem> = listOf()
    ): Int = when {
        expressionItems.isEmpty() ->
            (acc.first() as Num).value
        acc.isEmpty() || acc.last() is Num -> {
            // 기호
            val head = expressionItems.first()
            calculateActual(expressionItems.drop(1), acc + head)
        }
        else -> {
            val first = acc.first() as Num
            val second = expressionItems.first() as Num
            val operator = acc.last() as Operators
            calculateActual(
                expressionItems.drop(1),
                listOf(Num(operator.calculate(first.value, second.value)))
            )
        }
    }
}
