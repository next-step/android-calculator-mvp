package com.nextstep.edu.domain

import java.util.Optional

class Expression(
    operands: String = ""
) {

    private val calculator = Calculator
    private var operands: String = ""

    init {
        this.operands = operands
    }

    fun setOperands(operand: String) {
        operands += "$operand"
    }

    fun setMethods(operand: String) {
        operands += " $operand "
    }

    fun getOperands(): String = operands

    fun removeOperands() {
        if (operands.last().toString().isBlank()) {
            removeMethods()
        } else {
            removeNumber()
        }
    }

    private fun removeNumber() {
        operands = Optional.ofNullable(operands)
            .filter { str: String -> str.isNotEmpty() }
            .map { str: String -> str.substring(0, str.length - 1) }
            .orElse(operands)
    }

    private fun removeMethods() {
        operands = Optional.ofNullable(operands)
            .filter { str: String -> str.isNotEmpty() }
            .map { str: String -> str.substring(0, str.length - 3) }
            .orElse(operands)
    }

    fun resetMemory() {
        operands = ""
    }

    fun resultCalculate() {
        operands = calculator.evaluate(operands).toString()
    }

}