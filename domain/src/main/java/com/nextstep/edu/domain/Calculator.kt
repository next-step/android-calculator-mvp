package com.nextstep.edu.domain

import com.nextstep.edu.domain.Operators.Companion.toCalculate

object Calculator {

    fun evaluate(inputValue: String): Int {
        val splitInputValue = CalculationExpression.split(inputValue)
        CalculationExpression.validate(splitInputValue)

        var resultValue = splitInputValue[0]

        for (index in 1 until splitInputValue.size step 2) {
            resultValue = toCalculate(
                method = splitInputValue[index],
                first = resultValue,
                second = splitInputValue[index + 1]
            ).toString()
        }

        return resultValue.toInt()
    }
}