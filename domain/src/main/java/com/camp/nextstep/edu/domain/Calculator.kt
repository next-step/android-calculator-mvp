package com.camp.nextstep.edu.domain

class Calculator {
    private var result: Int = 0

    fun evaluate(s: String): Int {
        if (s.isEmpty()) throw IllegalArgumentException("string is empty")

        val tokens = s.split(" ")
        var firstOperand: Int? = null
        var secondOperand: Int? = null
        var operatorStr: String = ""

        for (token in tokens) {
            if (token.matches(Regex("""\d+"""))) {
                // 숫자인 경우
                if (null == firstOperand) {
                    firstOperand = token.toInt()
                } else {
                    secondOperand = token.toInt()

                    operation(
                        firstOperand = firstOperand,
                        secondOperand = secondOperand,
                        operatorStr = operatorStr
                    )

                    firstOperand = result
                }
            } else { //if(token.matches(Regex("""[-+*/]"""))) {
                // 사칙 연산 기호
                operatorStr = token
            }
        }
        return result
    }

    private fun operation(
        firstOperand: Int,
        secondOperand: Int,
        operatorStr: String
    ) = when(operatorStr) {
        "+" -> add(firstOperand, secondOperand)
        "-" -> minus(firstOperand, secondOperand)
        "*" -> multiply(firstOperand, secondOperand)
        "/" -> divide(firstOperand, secondOperand)
        else -> throw IllegalArgumentException("Invalid operator: $operatorStr")
    }

    private fun add(firstOperand: Int, secondOperand: Int) {
        result = firstOperand + secondOperand
    }
    private fun minus(firstOperand: Int, secondOperand: Int) {
        result = firstOperand - secondOperand
    }
    private fun multiply(firstOperand: Int, secondOperand: Int) {
        result = firstOperand * secondOperand
    }
    private fun divide(firstOperand: Int, secondOperand: Int) {
        result = firstOperand / secondOperand
    }


}