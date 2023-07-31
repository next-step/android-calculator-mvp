package com.example.calculatorlib

enum class Operator(val operator: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLIED("*"),
    DIVIDED("/");

    fun calculator(result: Int, num: Int) = when(Operator.find(operator)) {
        PLUS -> addition(result, num)
        MINUS -> subtraction(result, num)
        MULTIPLIED -> multiplication(result, num)
        DIVIDED -> division(result, num)
    }
    companion object {
        fun find(operator: String): Operator {
            return values().find { it.operator == operator } ?: throw IllegalArgumentException("사칙연산 기호가 아닌 경우 IllegalArgumentException 발행")
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