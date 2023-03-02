package com.nextstep.calculator

/**
 * @author 박소연
 * @created 2023/02/14
 * @updated 2023/02/14
 * @desc UI에 표현될 수식을 만드는 객체
 */
class Expression {
    private var expression = ""
    private var lastIsOperator: Boolean? = null

    // 수식에 정수 추가
    fun checkInput(input: Int) { addNumberToExpression(input) }

    // 수식에 연산자 추가
    fun checkInput(input: Operator) { addOperatorToExpression(input) }

    // 수식에 숫자 추가
    private fun addNumberToExpression(input: Int) {
        if (lastIsOperator == true) {
            expression += " "
        }
        expression += input.toString()
        lastIsOperator = false
    }

    // 수식에 사칙연산자 추가
    private fun addOperatorToExpression(input: Operator) {
        when (lastIsOperator) {
            true -> expression = expression.substring(0, expression.length - 1) + input.operator
            false -> expression += " ${input.operator}"
            else -> {}
        }
        lastIsOperator = true
    }

    // 마지막 입력값을 제거
    fun removeInput() {
        val value = expression.split(" ")

        if (expression.isNotEmpty()) {
            if (value.last().length > 1) {
                removeSingleInput()
            } else {
                removeInputIncludeSpace()
            }
        }
    }

    private fun removeSingleInput() {
        expression = expression.substring(0, expression.length - 1)
        lastIsOperator = false
    }

    private fun removeInputIncludeSpace() {
        expression = expression.substring(0, expression.length - 2)
        lastIsOperator = true
    }

    fun getInputExpression() = expression
}
