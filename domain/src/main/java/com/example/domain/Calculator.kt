package com.example.domain

class Calculator {
    fun evaluate(expressions: String?): Int {
        require(!expressions.isNullOrEmpty()) { "입력값이 존재하지 않습니다." }

        val expressionList = expressions.split(" ")
        require(expressionList.size % 2 == 1) { "불완전한 수식입니다." }

        var result = Operand.of(expressionList[0])

        for (i in 1 until expressionList.size step 2) {
            val operand = Operand.of(expressionList[i + 1])
            val operator = Operator.of(expressionList[i])

            result = operator.operate(result, operand)
        }
        return result
    }
}