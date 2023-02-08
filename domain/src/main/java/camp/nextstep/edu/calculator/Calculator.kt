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
        require(!newText.isNullOrEmpty())
        require(isAllCheck(newText))

        val lastChar = preText.split(" ").last { it.isNotEmpty() }
        val displayText = when {
            preText == "0" && isNumeric(newText) -> newText
            isNumeric(lastChar) && isOperation(newText) -> "$preText $newText "
            isOperation(lastChar) && isOperation(newText) -> "${preText.replaceRange(preText.length - 3, preText.length, " $newText ")}"
            else -> "$preText$newText"
        }

        return displayText
    }

    private fun isAllCheck(toCheck: String) = """[\S\d-+*/]""".toRegex().matches(toCheck)

    private fun isOperation(toCheck: String) = """[-+*/]""".toRegex().matches(toCheck)

    private fun isNumeric(toCheck: String) = toCheck.all { char -> char.isDigit() }
}