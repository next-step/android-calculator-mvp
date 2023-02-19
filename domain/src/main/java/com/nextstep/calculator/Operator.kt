package com.nextstep.calculator

/**
 * @author 박소연
 * @created 2023/02/06
 * @updated 2023/02/06
 * @desc 사칙연산 기호의 enum class
 */
enum class Operator(val char: Char, val result: (Int, Int) -> Int) {
    PLUS('+', { num1, num2 -> num1 + num2 }),
    MINUS('-', { num1, num2 -> num1 - num2 }),
    MULTIPLY('*', { num1, num2 -> num1 * num2 }),
    DIVIDE(
        '/',
        { num1, num2 ->
            require(num2 != 0) {
                "0으로 나눌 수 없다"
            }
            num1 / num2
        }
    );

    fun of(operator: Char): Operator? {
        return values().find { it.char == operator }
    }
}
