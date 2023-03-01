package camp.nextstep.edu.calculator

class Expression(
    private val calculator: Calculator
) {
    private val expressions = mutableListOf<String>()
    val text: String
        get() = expressions.joinToString(" ")

    fun updateExpressions(input: String) {
        if (expressions.isEmpty()) {
            expressions.add(input)
            return
        }
        val last = expressions.last()

        if (isInt(last) && isInt(input)) {
            expressions[expressions.lastIndex] =
                ((last.toInt() * 10) + input.toInt()).toString()
        } else {
            expressions.add(input)
        }
    }


    fun deleteLast() {
        if (expressions.isEmpty()) {
            return
        }
        val exp = expressions.last()
        if (isInt(exp) && exp.length > MINIMUM_NUMBER) {
            val newExp = exp.substring(0, exp.length - 1)
            expressions[expressions.lastIndex] = newExp
        } else {
            expressions.removeLast()
        }

    }

    fun calculateAndReset() {
        val result = calculator.run(text)
        expressions.clear()
        expressions.add(result.toString())
    }

    private fun isInt(input: String): Boolean {
        return input.toIntOrNull() != null
    }

    companion object {
        const val MINIMUM_NUMBER = 1
    }
}