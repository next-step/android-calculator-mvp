package com.example.calculatorlib

enum class Operator(val operator: String, val calculator: (Int, Int) -> Int) {
    PLUS("+", { left, right -> left + right }),
    MINUS("-", { left, right -> left - right }),
    MULTIPLIED("*", { left, right -> left * right }),
    DIVIDED("/", { left, right -> left / right });

    companion object {
        fun find(operator: String): Operator {
            return values().find { it.operator == operator }
                ?: throw IllegalArgumentException("사칙연산 기호가 아닌 경우 IllegalArgumentException 발행 - 입력값 : $operator")
        }

        // 덧셈
        fun addition(num1: Int, num2: Int) = num1 + num2

        // 뺄셈
        fun subtraction(num1: Int, num2: Int) = num1 - num2

        // 곱셈
        fun multiplication(num1: Int, num2: Int) = num1 * num2

        // 나눗셈
        fun division(num1: Int, num2: Int) = num1 / num2
    }
}