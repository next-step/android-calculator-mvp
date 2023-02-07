package com.example.domain

class Calculator {
    fun evaluate(expression: String): Int {
        require(expression.isNotEmpty()) { "입력 값이 존재 하지 않습니다." }

        val calculationFormula = isValidExpression(expression.split(" "))

        var result = calculationFormula[0].toInt()
        for (i: Int in 1 until calculationFormula.size step 2) {
            println(calculationFormula[i])
            val operator = Operator.of(calculationFormula[i])
            result = operator.evaluator(result, calculationFormula[i + 1].toInt())
        }

        return result
    }

    private fun isValidExpression(expression: List<String>): List<String> {
        require(expression.size % 2 != 0) { "표현식이 옳지 않습니다." }
        expression.forEachIndexed { index, value ->
            if ((index % 2 == 0)) {
                try {
                    value.toInt()
                } catch (e: Exception) {
                    throw IllegalArgumentException()
                }
            }

            if ((index % 2 == 1) &&
                value.isNullOrEmpty()
            ) {
                throw IllegalArgumentException("표현식이 옳지 않습니다.")
            }
        }

        return expression
    }
}