package com.example.calculatorlib

class Calculator {
    fun evaluate(str: String?): Int {

        require(!str.isNullOrEmpty()) {
            "문자열 수식(str)이 null 혹은 공백일 경우 IllegalArgumentException 발행"
        }

        val numAndOperator = str.split(" ")

        // 첫번쨰 숫자
        var result = numAndOperator[0].toInt()

        for(i in 1 until numAndOperator.size step 2) {
            val operator = Operator.find(numAndOperator[i])
            val num = numAndOperator[i+1]

            result = operator.calculator(result, num.toInt())
        }
        return result
    }
}