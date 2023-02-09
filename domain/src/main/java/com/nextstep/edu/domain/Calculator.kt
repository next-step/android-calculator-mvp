package com.nextstep.edu.domain

class Calculator {
    fun calculate(inputValue: String): Int {
        val calculationExpression = CalculationExpression(inputValue)
        val splitInputValue = calculationExpression.value

        var accumulatedValue = splitInputValue[0].toInt()
        var operatorSymbol: Operator

        for (index in 1 until splitInputValue.size step 2) {
            operatorSymbol = Operator.of(splitInputValue[index])
            accumulatedValue =
                operatorSymbol.execute(accumulatedValue, splitInputValue[index + 1].toInt())
        }

        return accumulatedValue
    }
}