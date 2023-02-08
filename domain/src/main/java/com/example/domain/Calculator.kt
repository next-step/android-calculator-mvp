package com.example.domain

class Calculator {
    fun evaluate(expressions: String?): Int {
        val expressionList = parseExpressions(checkEmptyExpressions(expressions))

        var result = Operand.of(expressionList[0])

        for (i in 1 until expressionList.size step 2) {
            val operand = Operand.of(expressionList[i + 1])
            val operator = Operator.of(expressionList[i])

            result = operator.operate(result, operand)
        }
        return result
    }

    private fun checkEmptyExpressions(expressions: String?): String{
        require(!expressions.isNullOrEmpty()) { "입력값이 존재하지 않습니다." }

        return expressions
    }

    private fun parseExpressions(expressions: String): List<String> {
        val expressionList = expressions.split(" ")
        require(expressionList.size % 2 == 1) { "불완전한 수식입니다." }

        return expressionList
    }
}