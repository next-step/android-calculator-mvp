package camp.nextstep.edu.domain.calculator


class Calculator {

    fun evaluate(expression: List<ExpressionItem>): Int {
        ExpressionValidator().validOrError(expression)
        return calculateActual(expression)
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
            val symbol = acc.last() as Operators
            calculateActual(
                expressionItems.drop(1),
                listOf(Num(symbol.calculate(first.value, second.value)))
            )
        }
    }
}
