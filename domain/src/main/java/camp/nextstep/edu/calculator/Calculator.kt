package camp.nextstep.edu.calculator

const val plus = "+"

const val minus = "-"

const val multiply = "*"

const val divide = "/"

class Calculator {

    fun plus(value1: Double, value2: Double) = value1 + value2

    fun minus(value1: Double, value2: Double) = value1 - value2

    fun multiply(value1: Double, value2: Double) = value1 * value2

    fun divide(value1: Double, value2: Double) = value1 / value2

    fun evaluate(calculation: String): Double {
        if (calculation.isBlank() || calculation.last().isWhitespace()) {
            throw IllegalStateException()
        }

        val operationList = calculation.split(" ")
        var result = operationList[0].toDouble()

        for (i in 2..operationList.size step 2) {
            val operator = operationList[i - 1]
            result = calculate(result, operationList[i].toDouble(), operator)
        }

        return result
    }

    private fun calculate(value1: Double, value2: Double, operator: String) = when (operator) {
        plus -> plus(value1, value2)
        minus -> minus(value1, value2)
        multiply -> multiply(value1, value2)
        divide -> divide(value1, value2)
        else -> throw IllegalArgumentException()
    }

    fun displayCalculation(preText: String, newText: String?): String {
        require(!newText.isNullOrBlank())
        require(isAllCheck(newText))

        val lastChar = if (preText.isBlank()) "" else preText.split(" ").last { it.isNotBlank() }
        val displayText = when {
            preText.isEmpty() && isNumeric(newText) -> newText
            preText.isEmpty() && isOperation(newText) -> ""
            isOperation(lastChar) && isOperation(newText) -> "${preText.replaceRange(preText.length - 3, preText.length, " $newText ")}"
            isNumeric(lastChar) && isOperation(newText) -> "$preText $newText "
            else -> "$preText$newText"
        }

        return displayText
    }

    fun clearCalculation(calculation: String) = if (calculation.last().isDigit()) clearOperand(calculation) else clearOperator(calculation)

    private fun clearOperand(calculation: String) = calculation.dropLast(1)

    private fun clearOperator(calculation: String) = calculation.dropLast(3)

    companion object {
        private val allCheckRegex = """[\S\d-+*/]""".toRegex()

        private val operationCheckRegex = """[-+*/]""".toRegex()
    }

    private fun isAllCheck(toCheck: String) = allCheckRegex.matches(toCheck)

    private fun isOperation(toCheck: String) = operationCheckRegex.matches(toCheck)

    private fun isNumeric(toCheck: String) = toCheck.all { char -> char.isDigit() }
}
