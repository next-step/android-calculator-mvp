package com.nextstep.edu.domain

enum class Operator(private val symbol: String) {
    ADDITION("+") {
        override fun execute(leftOperand: Int, rightOperand: Int): Int = leftOperand + rightOperand
    },
    SUBTRACT("-") {
        override fun execute(leftOperand: Int, rightOperand: Int): Int = leftOperand - rightOperand
    },
    MULTIPLY("*") {
        override fun execute(leftOperand: Int, rightOperand: Int): Int = leftOperand * rightOperand
    },
    DIVIDE("/") {
        override fun execute(leftOperand: Int, rightOperand: Int): Int = leftOperand / rightOperand
    };

    abstract fun execute(leftOperand: Int, rightOperand: Int): Int

    companion object {
        fun of(symbol: String): Operator {
            val validSymbol = values().find {
                it.symbol == symbol
            }

            require(validSymbol != null) { "입력 기호가 사칙연산 기호가 아닙니다." }

            return validSymbol
        }
    }
}