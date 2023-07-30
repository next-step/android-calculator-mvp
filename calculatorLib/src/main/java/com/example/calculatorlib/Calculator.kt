package com.example.calculatorlib


class Calculator {
    fun evaluate(str: String?): Int {
        // 문자열 수식(str)이 null일 경우 IllegalArgumentException 발행
        val numAndOperator = str?.split(" ") ?: throw IllegalArgumentException()

        // 문자열 수식(str)이 공백일 경우 IllegalArgumentException 발행
        if(str.trim() == "")  throw IllegalArgumentException()

        // 첫번쨰 숫자
        var result = numAndOperator[0].toInt()

        for(i in 1 until numAndOperator.size step 2) {
            val operator = numAndOperator[i]
            val num = numAndOperator[i+1]

            if(!operatorVerification(operator)) throw IllegalArgumentException()

            when(operator) {
                Operator.PLUS.operator -> result = addition(result, num.toInt())
                Operator.MINUS.operator -> result = subtraction(result, num.toInt())
                Operator.MULTIPLIED.operator -> result = multiplication(result, num.toInt())
                Operator.DIVIDED.operator -> result = division(result, num.toInt())
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

    private fun numberFormatVerification(num: String): Boolean {
        val exception = kotlin.runCatching { num.toInt() }.exceptionOrNull()
        return exception !is NumberFormatException
    }
}