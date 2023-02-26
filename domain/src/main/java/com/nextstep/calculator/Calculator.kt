package com.nextstep.calculator

import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

/**
 * @author 박소연
 * @created 2023/02/05
 * @updated 2023/02/08
 * @desc 계산 로직을 수행 하는 클래스
 */

class Calculator {
    fun calculate(expression: String): String {
        val values = expression.split(" ")

        // 입력값이 null이거나 빈 공백 문자일 경우
        require(expression.isNotEmpty()) { "입력값이 null이거나 빈 공백 문자 - 입력값: $expression" }
        // 수식의 요소 갯수가 짝수로 수식이 불완전한 경우
        require(values.size % 2 != 0) { "올바르지 않은 수식: $expression" }

        var result = values[0]

        for (i in 1..values.size - 2 step 2) {
            result = divideSubComponent("$result ${values[i]} ${values[i + 1]}").toString()
        }

        return result
    }

    // 수식 내 구성요소 분리
    private fun divideSubComponent(expression: String): Int {
        val value = expression.split(" ")

        return calculateSubExpression(
            num1 = value[0].toInt(),
            num2 = value[2].toInt(),
            operator = value[1]
        )
    }

    // 연산자 체크 및 계산
    private fun calculateSubExpression(num1: Int, num2: Int, operator: String): Int {
        val operator: Operator = Operator.of(operator)
            ?: throw IllegalArgumentException("사칙연산자가 아닌 기호 - char: $operator")

        return operator.result(num1, num2)
    }
}
