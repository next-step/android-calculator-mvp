package com.nextstep.edu.domain

import java.util.Optional

class Expression(
    operands: String = ""
) {

    private val calculator = Calculator
    var expressions: String
        private set

    init {
        this.expressions = operands
    }

    fun setOperands(operand: String) {
        expressions += "$operand"
    }

    fun setMethods(operand: String) {
        expressions += " $operand "
    }

    fun getOperands(): String = expressions

    fun removeLatestOperands() {
        if (expressions.isBlank()) return

        if (expressions.last().toString().isBlank()) {
            removeLatestMethods()
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

    private fun removeLatestMethods() {
        expressions = Optional.ofNullable(expressions)
            .filter { str: String -> str.isNotEmpty() }
            .map { str: String -> str.substring(0, str.length - 3) }
            .orElse(expressions)
    }

    fun resetMemory() {
        expressions = ""
    }

    fun resultCalculate() {
        expressions = calculator.evaluate(expressions).toString()
    }

}