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
        val stringList = calculation.split(" ")
        var result = stringList[0].toDouble()

        for (i in 2..stringList.size step 2) {
            val op = stringList[i - 1]
            result = when (op) {
                plus -> plus(result, stringList[i].toDouble())
                minus -> minus(result, stringList[i].toDouble())
                multiply -> multiply(result, stringList[i].toDouble())
                divide -> divide(result, stringList[i].toDouble())
                else -> throw IllegalArgumentException()
            }
        }

        return result
    }
}