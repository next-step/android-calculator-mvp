package com.nextstep.edu.domain

import com.nextstep.edu.domain.Operators.Companion.toCalculate


class Calculator {

    fun evaluate(inputValue: String): Int {
        val calculationExpression = CalculationExpression()
        val splitInputValue = calculationExpression.split(inputValue)
        calculationExpression.validate(splitInputValue)

        var resultValue = splitInputValue[0]

        for (index in 1 until splitInputValue.size step 2) {
            val calculateList = listOf(resultValue, splitInputValue[index], splitInputValue[index + 1])
            resultValue = calculateList.toCalculate().toString()
        }

        return resultValue.toInt()
    }
}