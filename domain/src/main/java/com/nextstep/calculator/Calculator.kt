package com.nextstep.calculator

import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/06
 * @desc 계산 로직을 수행 하는 클래스
 */

class Calculator {
    private val validNumber = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val validOperator = charArrayOf(PLUS.char, MINUS.char, MULTIPLY.char, DIVIDE.char)

    fun calculate(formula: String): Int {
        // 입력값이 null이거나 빈 공백 문자일 경우
        require(formula.isNotEmpty()) {
            IllegalArgumentException("입력값이 null이거나 빈 공백 문자")
        }

        return checkFormula(formula)
    }

    /**
     * 수식 내 문자 체크 후 수식 생성
     * */
    private fun checkFormula(formula: String): Int {
        var currentOperator: Char? = null
        var subFormula = ""

        for (i in formula.indices) {
            when (formula[i]) {
                in validNumber -> subFormula += formula[i]
                in validOperator -> {
                    if (currentOperator != null) {
                        subFormula = divideComponent(subFormula).toString()
                    }
                    subFormula += formula[i]
                    currentOperator = formula[i]
                }
                else -> throw IllegalArgumentException("사칙연산자가 아닌 기호")
            }
        }
        return divideComponent(subFormula)
    }

    // 수식 내 구성요소 분리
    private fun divideComponent(formula: String): Int {
        // 수식 내 연산자 위치
        val operatorIndex = formula.indexOfAny(validOperator, ignoreCase = false)

        val operator = formula[operatorIndex]
        val num1 = formula.substring(0, operatorIndex).toInt()
        val num2 = formula.substring(operatorIndex + 1).toInt()

        return calculateSubFormula(num1, num2, operator)
    }

    // 연산자 체크 및 계산
    private fun calculateSubFormula(num1: Int, num2: Int, operator: Char): Int {
        return when (operator) {
            PLUS.char -> plus(num1, num2)
            MINUS.char -> minus(num1, num2)
            MULTIPLY.char -> multiply(num1, num2)
            DIVIDE.char -> divide(num1, num2)
            // 사칙연산 기호가 아닌 경우
            else -> throw IllegalArgumentException("사칙연산자가 아닌 기호")
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
        require(num2 != 0) {
            IllegalArgumentException("0으로 나눌 수 없다")
        }
        return num1 / num2
    }
}
