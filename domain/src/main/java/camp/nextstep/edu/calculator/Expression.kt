package camp.nextstep.edu.calculator

class Expression(
    private val calculator: Calculator
) {
    private val expressions = mutableListOf<String>()
    val text: String
        get() = expressions.joinToString(" ")

    fun updateExpressions(input: String) {
        //입력된 피연산자가 없을때

        if (expressions.isEmpty()) {
            //입력이 숫자면
            if (isNumber(input)) {
                expressions.add(input)
            }

        }
        //입력된 피연산자가 있을때
        else {
            //입력이 숫자면
            val last = expressions.last()
            if (isNumber(last)) {
                if (isNumber(input)) {
                    expressions[expressions.lastIndex] =
                        ((last.toInt() * 10) + input.toInt()).toString()
                } else {
                    expressions.add(input)
                }
            } else {
                if (isNumber(input)) {
                    expressions.add(input)
                } else {
                    expressions.add(input)
                }

            }

        }

    }

    fun delete() {
        if (expressions.isNotEmpty()) {
            val exp = expressions.last()
            if (isNumber(exp) && exp.length > 1) {
                val newExp = exp.substring(0, exp.length - 1)
                expressions[expressions.lastIndex] = newExp
            } else {
                expressions.removeLast()
            }

        }
    }


    fun calculateAndReset() {
        val result = calculator.run(text)
        expressions.clear()
        expressions.add(result.toString())
    }

    private fun isNumber(input: String): Boolean {
        return input.toIntOrNull() != null
    }

}