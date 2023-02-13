package com.nextstep.calculator

import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/08
 * @desc 계산 로직을 수행 하는 클래스
 */

class Calculator {
    private val validNumber = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    private val validOperator = charArrayOf(PLUS.char, MINUS.char, MULTIPLY.char, DIVIDE.char)

    fun calculate(expression: String): Int {
        // 입력값이 null이거나 빈 공백 문자일 경우
        require(expression.isNotEmpty()) {
            IllegalArgumentException("입력값이 null이거나 빈 공백 문자 - 입력값: $expression")
        }

        return checkExpression(expression)
    }

    /**
     * 수식 내 문자 체크 후 수식 생성
     * */
    private fun checkExpression(expression: String): Int {
        var currentOperator: Char? = null
        var subExpression = ""

        for (c in expression) {
            checkCharacter(
                c,
                currentOperator,
                subExpression,
                { checkExpression -> subExpression = checkExpression },
                { checkCharacter -> currentOperator = checkCharacter }
            )
        }
        return divideComponent(subExpression)
    }

    // 수식 내 문자가 숫자인지 사친연산자인지 체크
    private fun checkCharacter(
        char: Char,
        currentOperator: Char?,
        subExpression: String,
        checkedExpression: (String) -> Unit,
        checkedChar: (Char) -> Unit
    ) {
        when (char) {
            in validNumber -> checkedExpression.invoke(subExpression + char)
            in validOperator -> {
                checkFirstOperator(currentOperator, subExpression) {
                    checkedExpression.invoke(it)
                }
                checkedExpression.invoke(subExpression + char)
                checkedChar.invoke(char)
            }
            else -> throw IllegalArgumentException("사칙연산자가 아닌 기호")
        }
    }

    // 첫번째로 나온 사칙연산자가 아니면 수식 구성요소 분리 요청
    private fun checkFirstOperator(
        currentOperator: Char?,
        subExpression: String,
        checkedExpression: (String) -> Unit
    ) {
        if (currentOperator != null) {
            val calculateSubResult = divideComponent(subExpression).toString()
            checkedExpression.invoke(calculateSubResult)
        }
    }

    // 수식 내 구성요소 분리
    private fun divideComponent(expression: String): Int {
        // 수식 내 연산자 위치
        val operatorIndex = expression.indexOfAny(validOperator, ignoreCase = false)

        val operator = expression[operatorIndex]
        val num1 = expression.substring(0, operatorIndex).toInt()
        val num2 = expression.substring(operatorIndex + 1).toInt()

        return calculateSubExpression(num1, num2, operator)
    }

    // 연산자 체크 및 계산
    private fun calculateSubExpression(num1: Int, num2: Int, operator: Char): Int {
        return when (operator) {
            PLUS.char -> PLUS.apply(num1, num2)
            MINUS.char -> MINUS.apply(num1, num2)
            MULTIPLY.char -> MULTIPLY.apply(num1, num2)
            DIVIDE.char -> DIVIDE.apply(num1, num2)
            // 사칙연산 기호가 아닌 경우
            else -> throw IllegalArgumentException("사칙연산자가 아닌 기호 - char: $operator")
        }
    }
}
