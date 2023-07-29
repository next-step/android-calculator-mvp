package com.example.calculatorlib

class Calculator {
    fun evaluate(str: String?): Int {
        val numAndOperator = str?.split(" ") ?: throw IllegalArgumentException()
        if(str.trim() == "")  throw IllegalArgumentException()

        // 첫번쨰 숫자
        var result = numAndOperator[0].toInt()

        for(i in 1 until numAndOperator.size step 2) {
            val operator = numAndOperator[i]
            val num = numAndOperator[i+1]

            if(!operatorVerification(operator)) throw IllegalArgumentException()

            when(numAndOperator[i]) {
                "+" -> result = addition(result, num.toInt())
                "-" -> result = subtraction(result, num.toInt())
                "*" -> result = multiplication(result, num.toInt())
                "/" -> result = division(result, num.toInt())
            }
        }
        return result
    }

    // 덧셈
    private fun addition(num1: Int, num2: Int) = num1 + num2

    // 뺄셈
    private  fun subtraction(num1: Int, num2: Int) = num1 - num2

    // 곱셈
    private  fun multiplication(num1: Int, num2: Int) = num1 * num2

    // 나눗셈
    private  fun division(num1: Int, num2: Int) = num1 / num2

    // 입력값 null 이거나 빈 공백 문자일 경우 IllegalArgumentException throw
    // 사칙연산 기호가 아닌 경우 IllegalArgumentException throw
    private fun operatorVerification(operator: String): Boolean {
        if(!(operator == "+" || operator == "-" ||operator == "*" ||operator == "/")) {
            return false
        }
        return true
    }
}