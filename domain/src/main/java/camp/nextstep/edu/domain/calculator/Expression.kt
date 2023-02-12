package camp.nextstep.edu.domain.calculator


class Expression(
    private val thunk: (String) -> Unit
) {

    private val _expressions = mutableListOf<ExpressionItem>()
    val expressions: List<ExpressionItem>
        get() = _expressions.toList()


    fun append(input: ExpressionItem) {
        expressions.lastOrNull()?.let { last ->
            when {
                last is Num && input is Num -> {
                    _expressions.removeLast()
                    _expressions.add(Num("${last.value}${input.value}".toInt()))
                }
                last !is Num && input !is Num -> {
                    _expressions.removeLast()
                    _expressions.add(input)
                }
                else -> {
                    _expressions.add(input)
                }

            }
        } ?: run {
            if (input is Num) {
                _expressions.add(input)
            }
        }

        thunk.invoke(toString())
    }

    fun removeLastExpression() {
        if (_expressions.isEmpty()) return

        when (val last = _expressions.last()) {
            is Operators -> _expressions.removeLast()
            is Num -> {
                val lastValue = last.value
                if (lastValue > 9) {
                    val dropped = lastValue.toString().dropLast(1)
                    _expressions.removeLast()
                    _expressions.add(Num(dropped.toInt()))
                } else {
                    _expressions.removeLast()
                }
            }
        }

        thunk.invoke(toString())
    }

    override fun toString() =
        expressions.joinToString(separator = " ", transform = ExpressionItem::toString)
}
