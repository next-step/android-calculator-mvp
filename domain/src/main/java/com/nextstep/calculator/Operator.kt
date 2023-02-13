package com.nextstep.calculator

import java.util.function.BinaryOperator

/**
 * @author 박소연
 * @created 2023/02/06
 * @updated 2023/02/06
 * @desc 사칙연산 기호의 enum class
 */
enum class Operator(val char: Char) : BinaryOperator<Int> {
    PLUS('+') {
        override fun apply(num1: Int, num2: Int): Int = num1 + num2
    },
    MINUS('-') {
        override fun apply(num1: Int, num2: Int): Int = num1 - num2
    },
    MULTIPLY('*') {
        override fun apply(num1: Int, num2: Int): Int = num1 * num2
    },
    DIVIDE('/') {
        override fun apply(num1: Int, num2: Int): Int {
            require(num2 != 0) {
                IllegalArgumentException("0으로 나눌 수 없다")
            }
            return num1 / num2
        }
    };

    abstract override fun apply(num1: Int, num2: Int): Int
}
