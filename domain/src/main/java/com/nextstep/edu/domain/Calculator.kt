package com.nextstep.edu.domain

class Calculator {
    fun calculate(inputValue: String): Int {
        val calculationExpression = CalculationExpression(inputValue)
        val splitInputValue = calculationExpression.getValue()

        var accumulatedValue = splitInputValue[0].toString().toInt()
        var operatorSymbol: Operator

        for (index in 1 until splitInputValue.size step 2) {
            operatorSymbol = Operator.of(splitInputValue[index].toString())
            accumulatedValue =
                operatorSymbol.execute(accumulatedValue, splitInputValue[index + 1].toString().toInt())
        }

        return accumulatedValue
    }
}