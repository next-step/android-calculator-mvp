package camp.nextstep.edu.domain.calculator


class Expression(
    private val thunk: (String) -> Unit
) {

    private val expressions = mutableListOf<ExpressionItem>()


    fun append(input: ExpressionItem) {
        expressions.lastOrNull()?.let { last ->
            when {
                last is Num && input is Num -> {
                    expressions.removeLast()
                    expressions.add(Num("${last.value}${input.value}".toInt()))
                }
                last !is Num && input !is Num -> {
                    expressions.removeLast()
                    expressions.add(input)
                }
                else -> {
                    expressions.add(input)
                }

            }
        } ?: run {
            if (input is Num) {
                expressions.add(input)
            }
        }

        thunk.invoke(toString())
    }

    fun removeLastExpression() {
        if (expressions.isEmpty()) return

        when (val last = expressions.last()) {
            is Operators -> expressions.removeLast()
            is Num -> {
                val lastValue = last.value
                if (lastValue > 9) {
                    val dropped = lastValue.toString().dropLast(1)
                    expressions.removeLast()
                    expressions.add(Num(dropped.toInt()))
                } else {
                    expressions.removeLast()
                }
            }
        }

        thunk.invoke(toString())
    }

    fun clear() = expressions.clear()

    fun get(): List<ExpressionItem> = expressions

    override fun toString() =
        expressions.joinToString(separator = " ", transform = ExpressionItem::toString)
}
