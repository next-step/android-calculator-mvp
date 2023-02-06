package com.nextstep.edu.calculator.domain

enum class Operator(val symbol: String) : CalculatorOperator {
    Plus("+") {
        override fun operate(first: Int, second: Int): Int = first + second
    },

    Minus("-") {
        override fun operate(first: Int, second: Int): Int = first - second
    },

    MultiBy("*") {
        override fun operate(first: Int, second: Int): Int = first * second
    },

    DividedBy("/") {
        override fun operate(first: Int, second: Int): Int = first / second
    };

    companion object {
        fun find(operator: String): Operator {
            val mOperator = values()
                .find { it.symbol == operator }

            require(mOperator != null) {
                "사칙연산 기호가 아닙니다."
            }

            return mOperator
        }
    }
}