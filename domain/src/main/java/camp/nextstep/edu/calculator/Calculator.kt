package camp.nextstep.edu.calculator

class Calculator {
    fun run(str: String?): Int {
        val expressions = filterValidInput(str).split(" ")
        var result = expressions[0].toInt()

        for (i in 1 until expressions.size step 2) {
            when (expressions[i]) {
                "+" -> result += expressions[i + 1].toInt()
                "-" -> result -= expressions[i + 1].toInt()
                "/" -> result /= expressions[i + 1].toInt()
                "*" -> result *= expressions[i + 1].toInt()
                else -> throw IllegalArgumentException("사칙 연산 기호가 아닙니다.")
            }
        }
        return result
    }

    private fun filterValidInput(str: String?): String {
        if (str == null || str == " ") {
            throw IllegalArgumentException("null 또는 공백 입력")
        }
        return str
    }

}