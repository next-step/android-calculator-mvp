package com.github.dodobest.domain

class Calculator {
    fun calculate(inputArray: List<String>): Double {
        var sum: Double = inputArray[0].toDouble()

        for (idx in 1 until inputArray.size step 2) {
            sum = Operation.convertToOperation(inputArray[idx]).calculate(sum, inputArray[idx+1].toDouble())
        }

        return sum
    }
}