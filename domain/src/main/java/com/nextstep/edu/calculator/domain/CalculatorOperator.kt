package com.nextstep.edu.calculator.domain

interface CalculatorOperator {
    fun operate(first: Int, second: Int): Int
}