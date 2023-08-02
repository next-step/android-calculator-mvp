package com.nextstep.edu.domain

import java.util.Optional

class Expression(
    initExpressions: String = "",
) {

    var expressions: String
        private set

    init {
        this.expressions = initExpressions
    }

    fun setExpressions(expression: String) {
        if (expression.toIntOrNull() == null) {
            setMethods(expression)
        } else {
            setOperands(expression)
        }
    }

    private fun setOperands(operand: String) {
        expressions += operand
    }

    private fun setMethods(method: String) {
        if (expressions.isBlank()) return
        expressions += " $method "
    }


    fun removeLatestExpression() {
        if (expressions.isBlank()) return

        if (expressions.last().toString().isBlank()) {
            removeLatestMethod()
        } else {
            removeLatestNumber()
        }
    }

    private fun removeLatestNumber() {
        expressions = Optional.ofNullable(expressions)
            .filter { str: String -> str.isNotEmpty() }
            .map { str: String -> str.substring(0, str.length - 1) }
            .orElse(expressions)
    }

    private fun removeLatestMethod() {
        expressions = Optional.ofNullable(expressions)
            .filter { str: String -> str.isNotEmpty() }
            .map { str: String -> str.substring(0, str.length - 3) }
            .orElse(expressions)
    }

    fun resetMemory() {
        expressions = ""
    }

}