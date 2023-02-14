package camp.nextstep.edu.domain.calculator


class Calculator {

    fun evaluate(expression: List<ExpressionItem>): Int {
        ExpressionValidator.validOrError(expression)
        return calculateActual(expression)
    }

    private tailrec fun calculateActual(
        expressionItems: List<ExpressionItem>,
        accumulator: List<ExpressionItem> = listOf()
    ): Int = when {
        expressionItems.isEmpty() ->
            (accumulator.first() as Num).value
        accumulator.isEmpty() || accumulator.last() is Num -> {
            // 기호
            val head = expressionItems.first()
            calculateActual(expressionItems.drop(1), accumulator + head)
        }
        else -> {
            val first = accumulator.first() as Num
            val second = expressionItems.first() as Num
            val symbol = accumulator.last() as Operators
            calculateActual(
                expressionItems.drop(1),
                listOf(Num(symbol.calculate(first.value, second.value)))
            )
        }
    }
}
