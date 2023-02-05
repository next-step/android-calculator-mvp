package com.nextstep.edu.domain

enum class Operator(private val symbol: String) {
    ADDITION("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    companion object {
        fun of(symbol: String) {
            val validSymbol = values().find {
                it.symbol == symbol
            }

            require(validSymbol != null) { "입력 기호가 사칙연산 기호가 아닙니다." }
        }
    }
}