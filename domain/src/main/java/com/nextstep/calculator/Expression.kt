package com.nextstep.calculator

/**
 * @author 박소연
 * @created 2023/02/14
 * @updated 2023/02/14
 * @desc
 */
class Expression {
    private var expression = ""
    private var lastIsOperator: Boolean? = null

    fun checkInput(input: String) {
        runCatching {
            addNumberToExpression(input.toInt())
        }.onFailure {
            Operator.of(input)?.let { addOperatorToExpression(it) }
        }
    }

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

    fun removeInput() {
        val value = expression.split(" ")

        if (expression.isNotEmpty()) {
            if (value.last().length > 1) { removeSingleInput() } else { removeInputIncludeSpace() }
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
