package com.example.domain

class Calculator {
    fun evaluate(expressions: String?): Int {
        val expressionList = parseExpressions(expressions)

        var result = Operand.of(expressionList[0])

        for (i in 1 until expressionList.size step 2) {
            val operand = Operand.of(expressionList[i + 1])
            val op = Operator.of(expressionList[i])

            result = op.operate(result, operand)
        }
        return result
    }

    private fun parseExpressions(expressions: String?): List<String> {
        if (expressions.isNullOrEmpty()) {
            require(!expressions.isNullOrEmpty()) { "입력값이 존재하지 않습니다." }
        }

        val expressionList = expressions.split(" ")
        require(checkOddSize(expressionList)) { "불완전한 수식입니다." }

        return expressionList
    }

    private fun checkOddSize(expressionList: List<String>): Boolean {
        return expressionList.size % 2 == 1
    }
}