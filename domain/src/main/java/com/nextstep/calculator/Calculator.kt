package com.nextstep.calculator

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/06
 * @desc 계산 로직을 수행 하는 클래스
 */

class Calculator {
    private val validNumber = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val validOperator = charArrayOf('+', '-', '*', '/')
    private var result: Int? = null

    fun calculate(formula: String) {
        // 입력값이 null이거나 빈 공백 문자일 경우
        if (formula.isEmpty()) {
            throw IllegalArgumentException()
        }

        checkFormula(formula)
    }

    /**
     * 수식 내 문자 체크 후 수식 생성
     * */
    private fun checkFormula(formula: String) {
        var currentOperator: Char? = null
        var subFormula = ""

        for (i in formula.indices) {
            when (formula[i]) {
                in validNumber -> subFormula += formula[i]
                in validOperator -> {
                    if (currentOperator != null) {
                        subFormula = checkOperator(subFormula).toString()
                    }
                    subFormula += formula[i]
                    currentOperator = formula[i]
                }
                else -> throw IllegalArgumentException()
            }
        }
        result = checkOperator(subFormula)
    }

    // 연산자 체크
    private fun checkOperator(formula: String): Int {
        // 수식 내 연산자 위치
        val operatorIndex = formula.indexOfAny(validOperator, ignoreCase = false)

        val operator = formula[operatorIndex]
        val num1 = formula.substring(0, operatorIndex).toInt()
        val num2 = formula.substring(operatorIndex + 1).toInt()

        return when (operator) {
            '+' -> plus(num1, num2)
            '-' -> minus(num1, num2)
            '*' -> multiply(num1, num2)
            '/' -> divide(num1, num2)
            // 사칙연산 기호가 아닌 경우
            else -> throw IllegalArgumentException()
        }
    }

    private fun plus(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    private fun minus(num1: Int, num2: Int): Int {
        return num1 - num2
    }

    private fun multiply(num1: Int, num2: Int): Int {
        return num1 * num2
    }

    private fun divide(num1: Int, num2: Int): Int {
        if (num2 == 0) {
            throw ArithmeticException()
        }
        return num1 / num2
    }

    // 계산된 결과를 반환
    fun getResult() = result
}
