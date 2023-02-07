package com.example.domain

enum class Operator(val op: String) {
    PLUS("+") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int = firstOperand + secondOperand
    },
    MINUS("-") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int = firstOperand - secondOperand
    },
    MULTIPLY("*") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int = firstOperand * secondOperand
    },
    DIV("/") {
        override fun operate(firstOperand: Int, secondOperand: Int): Int {
            return firstOperand / secondOperand
        }
    };

    abstract fun operate(firstOperand: Int, secondOperand: Int): Int
}