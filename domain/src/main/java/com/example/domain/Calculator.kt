package com.example.domain

class Calculator {
    fun evaluate(expression: String) : Int {
        val expression = expression.split(" ")
        var result = expression[0].toInt()

        for (i: Int in 1 until expression.size step 2) {
            result = Operator.valueOf(result, expression[i+1].toInt(), expression[i],)
        }

        return result
    }
}