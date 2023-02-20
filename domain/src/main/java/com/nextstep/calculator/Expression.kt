package com.nextstep.calculator

import com.nextstep.calculator.Operator.* // ktlint-disable no-wildcard-imports

/**
 * @author 박소연
 * @created 2023/02/14
 * @updated 2023/02/14
 * @desc
 */
class Expression {
    private val validNumber = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val validOperator =
        arrayOf(PLUS.operator, MINUS.operator, MULTIPLY.operator, DIVIDE.operator)

    private var expression = ""
    var lastIsOperator: Boolean? = null

    fun checkInput(input: String) {
        when (input) {
            in validNumber -> addNumberToExpression(input)
            in validOperator -> addOperatorToExpression(input)
            else -> {
                throw IllegalAccessException()
            }
        }
    }

    // 수식에 숫자 추가
    private fun addNumberToExpression(input: String) {
        expression += input
        lastIsOperator = false
    }

    // 수식에 사칙연산자 추가
    private fun addOperatorToExpression(input: String) {
        when (lastIsOperator) {
            true -> {
                expression = expression.substring(0, expression.length - 1) + input
            }
            false -> expression += input
            else -> {}
        }
        lastIsOperator = true
    }

    fun removeInput() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            lastIsOperator = expression.last().toString() in validOperator
        }
    }

    fun getInputExpression() = expression
}
