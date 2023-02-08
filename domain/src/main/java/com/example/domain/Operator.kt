package com.example.domain

enum class Operator(val op: String) {
    PLUS("+") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int =
            firstOperand + secondOperand
    },
    MINUS("-") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int =
            firstOperand - secondOperand
    },
    MULTIPLY("*") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int =
            firstOperand * secondOperand
    },
    DIV("/") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int {
            return firstOperand / secondOperand
        }
    };

    abstract fun operate(firstOperand: Int, secondOperand: Int): Int

    companion object {
        fun of(op: String): Operator {
            val validOperator = values().find { operator ->
                op == operator.op
            }

            require(validOperator != null) { "지원하지 않는 사칙연산 기호입니다." }
            return validOperator
        }
    }
}