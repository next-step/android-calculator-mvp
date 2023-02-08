package camp.nextstep.edu.calculator

class Calculator {
    fun run(str: String?): Int {
        val expressions = filterValidInput(str).split(" ")
        val numbers: List<Int> = filterNumbers(expressions)
        val operators: List<Operator> = filterOperators(expressions)
        checkValidExpressions(numbers, operators)
        val result = calculate(numbers, operators)

        return result
    }

    private fun calculate(numbers: List<Int>, operators: List<Operator>): Int {
        val numbers = numbers.toMutableList()
        operators.forEach {
            val p0 = numbers.removeFirst()
            val p1 = numbers.removeFirst()
            val result = when (it) {
                Operator.PLUS -> Operator.PLUS.applyAsInt(p0, p1)
                Operator.SUB -> Operator.SUB.applyAsInt(p0, p1)
                Operator.DIV -> Operator.DIV.applyAsInt(p0, p1)
                Operator.MUL -> Operator.MUL.applyAsInt(p0, p1)
            }
            numbers.add(0, result)
        }
        return numbers.first()
    }

    private fun filterValidInput(str: String?): String {
        if (str.isNullOrBlank()) {
            throw IllegalArgumentException("null 또는 공백 입력")
        }
        return str
    }

    private fun filterNumbers(expressions: List<String>) =
        expressions.mapNotNull { it.toIntOrNull() }

    private fun filterOperators(expressions: List<String>) =
        expressions.mapNotNull { Operator.toOperator(it) }

    private fun checkValidExpressions(numbers: List<Int>, operators: List<Operator>) {
        if (numbers.size - 1 != operators.size)
            throw IllegalArgumentException("유효하지 않은 수식 입니다.")
    }
}