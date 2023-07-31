package com.example.calculatorlib

enum class Operator(val operator: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLIED("*"),
    DIVIDED("/");

    companion object {
        fun find(operator: String): Operator {
            return values().find { it.operator == operator } ?: throw IllegalArgumentException("사칙연산 기호가 아닌 경우 IllegalArgumentException 발행")
        }
    }
}